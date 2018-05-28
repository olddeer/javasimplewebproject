package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;

public class ListTripsDispCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206921776340723558L;
	 private static final Logger LOG = Logger.getLogger(  ListTripsDispCommand.class);
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.trace("dest " + request.getSession().getAttribute("dest"));
		if(request.getSession().getAttribute("dest")!=null)
		{		Integer dest=Integer.parseInt(((String)request.getSession().getAttribute("dest")));
				Integer from=Integer.parseInt(((String)request.getSession().getAttribute("from")));
			SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
			Date dt;
			request.getSession().setAttribute("dest",null);
			try {
				dt = new Date(format.parse((String)request.getSession().getAttribute("date")).getTime());
				LOG.trace("date " + request.getParameter("date"));
				request.getSession().removeAttribute("date");
				Trip trp=new Trip();
				LocalDate now=LocalDate.now();
				
				trp.setDateCreation(Date.valueOf(now));
				trp.setDispatcher_id(((User)request.getSession().getAttribute("user")).getId());
				
				trp.setDestination_id(dest);
				trp.setFrom_id(from);
				trp.setStatusId(3);
				trp.setDateSetOff(dt);
				DBManager.getInstance().insertTrip(trp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Trip> trips = null;
		LOG.debug("Command starts");
		try {
			trips = DBManager.getInstance().findAllTrips();
			
			LOG.trace("Found in DB: menuTripList --> " + trips);
			System.out.println(trips);
			trips = DBManager.getInstance().findAllTrips();
			LOG.trace("Found in DB: menuTripList --> " + trips);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		request.getSession().setAttribute("tripsDisp", trips);
		   LOG.trace("kek");
		
		return Path.PAGE_LIST_TRIPS_DISP;
	}

}
