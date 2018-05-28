package ua.nure.sutyagin.SummartTask4.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Driver;

import ua.nure.sutyagin.SummaryTask4.enteties.Address;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoStatus;
import ua.nure.sutyagin.SummaryTask4.enteties.Broken_Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.CompletedRequest;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;
import ua.nure.sutyagin.SummaryTask4.exception.Messages;
import ua.nure.sutyagin.SummaryTask4.web.command.LoginCommand;

public class DBManager {
	private static DBManager instance;

	public DBManager() {
		super();

		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			// ST4DB - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/finaltask");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private DataSource ds;
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/finaltask" + "?user=test&password=localhost";
	// Update

	// Find
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_ALL_CARS = "SELECT * FROM autos";
	private static final String SQL_FIND_ALL_REQUESTS = "SELECT * FROM requests";
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	private static final String SQL_FIND_ALL_TRIPS = "SELECT * FROM trips";
	private static final String SQL_FIND_TRIPS_BY_STATUS = "SELECT * FROM trips WHERE status_id=?";
	private static final String SQL_FIND_ALL_AUTO_STATUSES = "SELECT * FROM auto_status";
	private static final String SQL_FIND_ALL_ADDRESS = "SELECT * FROM address";
	private static final String SQL_FIND_ADDRESS_BY_ID = "SELECT * FROM address WHERE address_id=?";
	private static final String SQL_FIND_ADDRESS_BY_NAME = "SELECT * FROM address WHERE name=?";
	
	
	// Insert
	private static final String SQL_INSERT_AUTO = "INSERT INTO autos VALUES  (DEFAULT, ?, ?, ?)";
	private static final String SQL_INSERT_USER = "CALL insert_user2( ?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_TRIP = "INSERT INTO trips VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_REQUEST = "INSERT INTO requests VALUES (DEFAULT, ?, ?, ?, ?)";
	private static final String SQL_INSERT_COMPLETED_REQUEST = "INSERT INTO completed_requests VALUES ( ?, ?, ?)";

	// Find with sort
	private static final String SQL_FIND_ALL_REQUESTS_BY_TRIP_STATUS = "SELECT * FROM `requests` WHERE trip_id IN (SELECT trip_id FROM trips WHERE status_id= ? AND dispatcher_id= ?)";
	private static final String SQL_FIND_ALL_REQUESTS_BY_TRIP_STATUS_2 = "SELECT * FROM `requests` WHERE trip_id IN (SELECT trip_id FROM trips WHERE status_id= ?)";
	private static final String SQL_FIND_ALL_COMPL_REQUESTS_BY_DRIVER_ID = "SELECT * FROM completed_requests WHERE completed_requests.request_id IN (SELECT requests.request_id FROM requests WHERE driver_id=? )";
	private static final String SQL_FIND_ALL_COMPL_REQUESTS_BY_ID = "SELECT * FROM completed_requests WHERE completed_requests.request_id=?";
	private static final String SQL_FIND_ALL_TRIPS_BY_STATUS = "SELECT * FROM `trips` ORDER BY status_id ";
	private static final String SQL_FIND_ALL_TRIPS_BY_ID = "SELECT * FROM `trips` ORDER BY trip_id ";
	private static final String SQL_FIND_ALL_TRIPS_BY_DATE = "SELECT * FROM `trips` ORDER BY date_creation ";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
	private static final String SQL_FIND_REQ_BY_USER_ID = "SELECT * FROM requests WHERE requests.driver_id=?";
	private static final String SQL_FIND_TRIP_BY_ID = "SELECT * FROM trips WHERE trip_id=?";
	private static final String SQL_FIND_REQ_BY_ID = "SELECT * FROM requests WHERE request_id = ?";
	private static final String SQL_FIND_AUTOS_BY_AUTO_TYPE = "SELECT * FROM autos WHERE auto_type = ? AND auto_status=1";
	private static final String SQL_FIND_AUTOS_BROKEN_AND_BUSY="SELECT * FROM autos WHERE auto_type = ? AND (auto_status=2 OR auto_status=3)";
	private static final String SQL_FIND_ALL_COMPLETED_REQUESTS = "SELECT * FROM completed_requests ";
	private static final String SQL_FIND_AUTO_BY_ID = "SELECT * FROM autos WHERE auto_id=?";
	
	// Update
	private static final String SQL_UPDATE_TRIP = "UPDATE trips SET status_id = ? WHERE trip_id = ?";
	private static final String SQL_UPDATE_AUTO = "UPDATE autos SET auto_status = ? WHERE auto_id = ?";
	private static final String SQL_UPDATE_USER="UPDATE users SET ban = ? WHERE user_id=? ";
	private static final String SQL_UPDATE_FULL_AUTO = "UPDATE autos SET auto_status = ?, name= ?, auto_type= ?  WHERE auto_id = ?";

	
	private static final String SQL_DELETE_AUTO = "DELETE FROM autos WHERE auto_id = ? ";
	private static final String SQL_DELETE_USER="DELETE FROM users WHERE user_id = ?";
	
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private static final String SQL_FIND_BROKE_AUTOS_BY_AUTO_ID = "SELECT * FROM broken_autos WHERE auto_id=?";
	private static final String SQL_FIND_BROKEN_AUTOS_BY_TYPE = "SELECT * FROM broken_autos WHERE auto_id IN (SELECT auto_id FROM autos WHERE auto_type=?)";
	private static final String SQL_INSERT_BROKEN_AUTO = "INSERT INTO broken_autos  VALUES  (DEFAULT, ?, ?)";
	private static final String SQL_INSERT_ADDRESS = "INSERT INTO address VALUES(DEFAULT,?)";
	private static final String SQL_UPDATE_ADDRESS = "UPDATE address SET name = ? WHERE address_id= ?";
	private static final String SQL_DELETE_ADDRESS = "DELETE FROM address WHERE address_id= ?";
	private static final String SQL_DELETE_REQUEST_BY_TRIP_ID = "DELETE FROM requests WHERE request_id= ? AND trip_id=?";
	public static synchronized DBManager getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new DBManager();
		}

		return instance;
	}
	public Broken_Auto findBrokenCar(int autoId) {
		Broken_Auto ba=null;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_BROKE_AUTOS_BY_AUTO_ID);
			pstmt.setInt(1, autoId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ba=extractBrokenAuto(rs);
			}
			con.commit();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return ba;	
	}
	
	public void insertBroken_Auto(Broken_Auto ba) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT_BROKEN_AUTO);
		stmt.setInt(1, ba.getAutoId());
		stmt.setDate(2, ba.getDateOfBroken());
		stmt.executeUpdate();
		con.commit();
		close(con);
		close(stmt);
		
	}
	public List<Broken_Auto> findAllBrokenCarsByType(int typeId){
		List<Broken_Auto> ba=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_BROKEN_AUTOS_BY_TYPE);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ba.add(extractBrokenAuto(rs));
			}
			con.commit();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return ba;
		
	}
	private Broken_Auto extractBrokenAuto(ResultSet rs) throws SQLException {
		Broken_Auto ba = new Broken_Auto();
		ba.setId(rs.getInt("id"));
		ba.setAutoId(rs.getInt("auto_Id"));
		ba.setDateOfBroken(rs.getDate("date_of_broken"));
		
		return ba;
	}
	public List<Auto> findAllBrokenOrBusyAutos(int  autoTypeId)throws DBException {
		
		List<Auto> autos = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_AUTOS_BY_AUTO_TYPE);
			pstmt.setInt(1, autoTypeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				autos.add(extractAuto(rs));
			}
			con.commit();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return autos;
		
	}
	
	public Connection getConnection() throws SQLException {
		Connection com = ds.getConnection();
		com.setAutoCommit(false);
		return com;
	}

	public List<Auto> findAllAutos() throws DBException {
		List<Auto> cars = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_CARS);
			while (rs.next()) {
				Auto auto = extractAuto(rs);
				cars.add(auto);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_AUTOS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_AUTOS, ex);

		} finally {
			close(con, stmt, rs);
		}

		return cars;
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
				// LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public List<Trip> findTripsByStatus() throws DBException {
		List<Trip> trips = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_TRIPS_BY_STATUS);
			while (rs.next()) {
				Trip trip = extractTrip(rs);
				trips.add(trip);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TRIPS_BY_STATUS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TRIPS_BY_STATUS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return trips;
	}

	public User findUserById(int id) throws DBException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User us = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {

				us = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			close(con, stmt, rs);
		}
		return us;
	}

	public Trip findTripById(int id) throws DBException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Trip trip = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(SQL_FIND_TRIP_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				trip = extractTrip(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_TRIP_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_TRIP_BY_ID, ex);
		} finally {
			close(con, stmt, rs);
		}
		return trip;

	}

	public Request findReqById(int id) throws DBException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Request req = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(SQL_FIND_REQ_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				req = extractRequset(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			
			
			
			
			
			
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQ_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQ_BY_ID, ex);
		} finally {
			close(con, stmt, rs);
		}
		return req;

	}

	public String insertUser(User user) throws DBException {
		Connection con = null;
		PreparedStatement stmt = null;

		String result=null;
		try {


			con = getConnection();
		
			List<User> list=this.findAllUsers();
			for(User x: list) {
			if(user.getLogin().trim().equals(x.getLogin())) {
				result = "Login is required";
				close(con);	
				return result;
			}
			
			}
			stmt = con.prepareCall(SQL_INSERT_USER);
			stmt.setString(3, user.getLogin().trim());
			stmt.setString(4, user.getPassword());
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getSecondName());
			stmt.setInt(5, user.getRoleId());
			
			stmt.executeUpdate();
			
			
			con.commit();

		} catch (SQLException ex) {
		  rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_INSERT_USER, ex);
		} finally {
			close(con);
			close(stmt);
			
		}
		return result;
		
	}

	
	public Address findAddressById(int id) throws DBException {
		Address adr = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ADDRESS_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			adr = extractAddress(rs);
			}
		con.commit();
	
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ADR_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ADR_BY_ID, ex);
		} finally {
			close(con);
			close(pstmt);
			close(rs);
		}
		
		return adr;
	}
	
	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			user = extractUser(rs);
			}
		con.commit();
	
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_FIND_USER_BY_LOGIN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FIND_USER_BY_LOGIN, ex);
		} finally {
			close(con);
			close(pstmt);
			close(rs);
		}
		
		return user;
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	
	

	
	public void insertAddress(Address adr)  {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
		 con = getConnection();
		 stmt = con.prepareStatement(SQL_INSERT_ADDRESS);
		stmt.setString(1, adr.getName());
		stmt.executeUpdate();
		con.commit();}
		catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
		}
		finally {
			close(con);
			close(stmt);
		}
	}


	
	
	public void insertAuto(Auto auto) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT_AUTO);
		stmt.setString(1, auto.getName());
		stmt.setInt(2, auto.getAutoTypeId());
		stmt.setInt(3, 1);
		stmt.executeUpdate();
		con.commit();
		close(con);
		close(stmt);
	}

	public void insertCompletedRequest(CompletedRequest req) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT_COMPLETED_REQUEST);
		stmt.setInt(1, req.getRequest_id());
		stmt.setInt(2, req.getAuto_id());
		stmt.setDate(3, req.getDateCompleted());
		stmt.executeUpdate();
		con.commit();
		close(con);
		close(stmt);
	}

	public void insertTrip(Trip trip) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT_TRIP);
		stmt.setDate(1, new Date(new java.util.Date().getTime()));
		stmt.setInt(2, trip.getStatusId());
		stmt.setInt(3, trip.getDestination_id());
		stmt.setDate(4, trip.getDateSetOff());
		stmt.setInt(5, trip.getFrom_id());
		stmt.setInt(6, trip.getDispatcher_id());
		stmt.executeUpdate();
		con.commit();
		close(con);
		close(stmt);
	}

	public void insertRequset(Request req) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL_INSERT_REQUEST);
		stmt.setDate(1, new Date(new java.util.Date().getTime()));
		stmt.setInt(2, req.getTripId());
		stmt.setInt(3, req.getAutoTypeId());
		stmt.setInt(4, req.getDriverId());
		stmt.executeUpdate();
		con.commit();
		close(con);
		close(stmt);
	}

	public List<Request> findAllRequests() throws SQLException {
		List<Request> reqs = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_REQUESTS);
		while (rs.next()) {
			Request req = extractRequset(rs);
			reqs.add(req);
		}
		con.commit();
		close(con, stmt, rs);
		return reqs;
	}

	public List<Request> findAllRequestsByStatusId(int statusId, int disp_id) throws SQLException {
		List<Request> reqs = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_REQUESTS_BY_TRIP_STATUS);
		pstmt.setInt(1, statusId);
		pstmt.setInt(2, disp_id);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Request req = extractRequset(rs);
			reqs.add(req);
		}
		con.commit();
		close(con, pstmt, rs);
		return reqs;
	}

	public List<CompletedRequest> findAllComplRequestsByDriverID(int drive_id) throws SQLException {
		List<CompletedRequest> reqs = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_COMPL_REQUESTS_BY_DRIVER_ID);
		pstmt.setInt(1, drive_id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CompletedRequest req = extractComplReq(rs);
			reqs.add(req);
		}
		con.commit();
		close(con, pstmt, rs);
		return reqs;
	}

	public CompletedRequest findAllComplRequestsById(int req_id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_COMPL_REQUESTS_BY_ID);
		pstmt.setInt(1, req_id);
		ResultSet rs = pstmt.executeQuery();
		CompletedRequest req = null;
		if (rs.next()) {
			req = extractComplReq(rs);

		}
		con.commit();
		close(con, pstmt, rs);
		return req;
	}

	public List<User> findAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
		while (rs.next()) {
			User user = extractUser(rs);
			users.add(user);
		}
		con.commit();
		close(con, stmt, rs);
		return users;
	}

	public List<Trip> findAllTrips() throws SQLException {
		List<Trip> trips = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_TRIPS);
		while (rs.next()) {
			Trip trip = extractTrip(rs);
			trips.add(trip);
		}
		con.commit();
		close(con, stmt, rs);
		return trips;
	}

	public List<AutoStatus> findAllAutoStatuses() throws SQLException {
		List<AutoStatus> listStat = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_AUTO_STATUSES);
		while (rs.next()) {
			AutoStatus stat = extractAutoStatus(rs);
			listStat.add(stat);
		}
		con.commit();
		close(con, stmt, rs);
		return listStat;
	}

	private AutoStatus extractAutoStatus(ResultSet rs) throws SQLException {
		AutoStatus st = AutoStatus.values()[rs.getInt("id") - 1];

		return st;
	}

	public List<Trip> findAllTrips(int statusId) throws SQLException {
		List<Trip> trips = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = con.prepareStatement(SQL_FIND_TRIPS_BY_STATUS);
		pstmt.setInt(1, statusId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			trips.add(extractTrip(rs));
		}
		con.commit();
		close(con, pstmt, rs);
		return trips;
	}

	public Auto findAutoById(int autoId) {
		Auto auto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_AUTO_BY_ID);
			pstmt.setInt(1, autoId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				auto = extractAuto(rs);
			}
			con.commit();
			close(con);
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return auto;

	}

	public List<Auto> findAllAuto(int autoTypeId) throws SQLException {
		List<Auto> autos = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = con.prepareStatement(SQL_FIND_AUTOS_BY_AUTO_TYPE);
		pstmt.setInt(1, autoTypeId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			autos.add(extractAuto(rs));
		}
		con.commit();
		close(con, pstmt, rs);
		return autos;
	}

	public List<Address> findAllAdr() throws SQLException {
		 List<Address> adrList = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_ADDRESS);
		while (rs.next()) {
			Address adr = extractAddress(rs);
			adrList.add(adr);
		}
		con.commit();
		close(con);
		close(stmt);
		close(rs);
		return adrList;

	}
	
	public List<CompletedRequest> findAllCompReq() throws SQLException {

		List<CompletedRequest> compList = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_COMPLETED_REQUESTS);
		while (rs.next()) {
			CompletedRequest req = extractComplReq(rs);
			compList.add(req);
		}
		con.commit();
		close(con);
		close(stmt);
		close(rs);
		return compList;

	}

	public List<Trip> findAllTripsByDate() throws SQLException {
		List<Trip> trips = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_TRIPS_BY_DATE);
		while (rs.next()) {
			Trip trip = extractTrip(rs);
			trips.add(trip);
		}
		con.commit();
		close(con);
		close(stmt);
		close(rs);
		return trips;
	}
public void updateUser(User us)throws SQLException {
	Connection con= getConnection();
	PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
	pstmt.setBoolean(1, us.isBan());
	pstmt.setInt(2, us.getId());
	pstmt.executeUpdate();
	con.commit();
	close(con);
	close(pstmt);
	
}
	
	public void updateTrip(Trip trip) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_TRIP);
		pstmt.setInt(1, trip.getStatusId());
		pstmt.setInt(2, trip.getId());
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}

	public Request findRequestById(int reqId) {
		Request req = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_REQ_BY_ID);
			pstmt.setInt(1, reqId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				req = extractRequset(rs);
			}
			con.commit();
			close(con);
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return req;
	}
	public void updateAddress(Address adr)  {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
		 con = getConnection();
		 stmt = con.prepareStatement(SQL_UPDATE_ADDRESS);
		stmt.setString(1, adr.getName());
		stmt.setInt(2, adr.getId());
		stmt.executeUpdate();
		con.commit();}
		catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
		}
		finally {
			close(con);
			close(stmt);
		}
	}

	public void deleteAddress(int adr)  {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
		 con = getConnection();
		 stmt = con.prepareStatement(SQL_DELETE_ADDRESS);
		
		stmt.setInt(1, adr);
		stmt.executeUpdate();
		con.commit();}
		catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
		}
		finally {
			close(con);
			close(stmt);
		}
	}
	
	public void updateFullAuto(Auto auto) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_FULL_AUTO);

		pstmt.setInt(1, auto.getAutoStatusId());
		pstmt.setString(2, auto.getName());
		pstmt.setInt(3, auto.getAutoTypeId());
		pstmt.setInt(4, auto.getId());
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}
	public void updateAuto(Auto auto) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_AUTO);

		pstmt.setInt(1, auto.getAutoStatusId());
		pstmt.setInt(2, auto.getId());
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}

	public List<Request> findAllDriveReq(User us) {
		List<Request> reqs = null;
		try {
			reqs = new ArrayList<>();
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_REQ_BY_USER_ID);
			pstmt.setInt(1, us.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Request req = extractRequset(rs);
				reqs.add(req);
			}
			con.commit();
			close(con, pstmt, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reqs;
	}

	public void deleteAuto(int carId) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_AUTO);
		pstmt.setInt(1, carId);
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}
	public void deleteReq(int reqId,int  trip_id) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_REQUEST_BY_TRIP_ID);
		pstmt.setInt(1, reqId);
		pstmt.setInt(2, trip_id);
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}
	public void deleteUser(int userId) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER);
		pstmt.setInt(1, userId);
		pstmt.executeUpdate();
		con.commit();
		close(con);
		close(pstmt);
	}
	private Trip extractTrip(ResultSet rs) throws SQLException {
		Trip trip = new Trip();
		trip.setDateCreation(rs.getDate("date_creation"));
		trip.setDateSetOff(rs.getDate("date_set_off"));
		trip.setDestination_id(rs.getInt("destination_id"));
		trip.setDispatcher_id(rs.getInt("dispatcher_id"));
		trip.setFrom_id(rs.getInt("from_id"));
		trip.setId(rs.getInt("trip_id"));
		trip.setStatusId(rs.getInt("status_id"));

		return trip;
	}
	
	
	
private Address extractAddress(ResultSet rs)throws SQLException {
	Address adr=new Address();
	adr.setId(rs.getInt("address_id"));
	adr.setName(rs.getString("name"));
	return adr;
}
	
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setFirstName(rs.getString("first_name"));
		user.setSecondName(rs.getString("last_name"));
		user.setId(rs.getInt("user_id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setRoleId(rs.getInt("role_id"));
		user.setBan(rs.getBoolean("ban"));
		
		return user;
	}

	private Request extractRequset(ResultSet rs) throws SQLException {
		Request req = new Request();
		req.setId(rs.getInt("request_id"));
		req.setAutoTypeId(rs.getInt("auto_type_id"));
		req.setDateCreation(rs.getDate("date_creation"));
		req.setDriverId(rs.getInt("driver_id"));
		req.setTripId(rs.getInt("trip_id"));

		return req;
	}

	private Auto extractAuto(ResultSet rs) throws SQLException {
		Auto auto = new Auto();
		auto.setAutoTypeId(rs.getInt("auto_type"));
		auto.setName(rs.getString("name"));
		auto.setId(rs.getInt("auto_id"));
		auto.setAutoStatusId(rs.getInt("auto_status"));

		return auto;
	}

	private CompletedRequest extractComplReq(ResultSet rs) throws SQLException {
		CompletedRequest cr = new CompletedRequest();
		cr.setAuto_id(rs.getInt("auto_id"));
		cr.setDateCompleted(rs.getDate("date_completed"));
		cr.setRequest_id(rs.getInt("request_id"));

		return cr;
	}

	public List<Request> findAllRequestsByStatusId(int statusId) throws SQLException {
		List<Request> reqs = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_REQUESTS_BY_TRIP_STATUS_2);
		pstmt.setInt(1, statusId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Request req = extractRequset(rs);
			reqs.add(req);
		}
		con.commit();
		close(con, pstmt, rs);
		return reqs;
	}

}
