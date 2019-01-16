package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoType;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class MakeRequestCommand extends Command{
	 private static final Logger LOG = Logger.getLogger(MakeRequestCommand.class);
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5116556923859384216L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		if((Request)request.getSession().getAttribute("req")!=null ||request.getSession().getAttribute("tripId")!=null) {
		Request req=(Request)request.getSession().getAttribute("req");
		request.getSession().removeAttribute("req");
		req.setTripId(Integer.parseInt((String) request.getSession().getAttribute("tripId")));
		request.getSession().removeAttribute("tripId");
		req.setAutoTypeId((String)request.getSession().getAttribute("autoTypeId"));
		Trip trp=null;
		LOG.trace("Start MakeReq with id ->"+ req.getTripId());
		try {
			 LocalDate now = LocalDate.now();
		   
			 LOG.trace("user id"+req.getDriverId());
		trp=DBManager.getInstance().findTripById(req.getTripId());
		 LOG.trace("trip id "+ trp.getId());
		trp.setStatusId(3);
		DBManager.getInstance().deleteReq(req.getId(), req.getTripId());
		DBManager.getInstance().updateTrip(trp);
		LOG.trace("Trip has been updated");
		 DBManager.getInstance().insertRequset(req);
		 LOG.trace("Request was inserted "+req);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		}
		List<Request> listReq=new ArrayList<>();
		try {
			 listReq=DBManager.getInstance().findAllDriveReq((User)request.getSession().getAttribute("user"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("listReq", listReq);
		LOG.trace("list of req was added to session attribute "+listReq);
		return Path.PAGE_MAKE_REQUEST;
	}

}
