package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoType;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;

import org.apache.log4j.Logger;

public class ListTripsCommand extends Command {

	 private static final Logger LOG = Logger.getLogger(ListTripsCommand.class);
	private static final long serialVersionUID = -8549076686047769583L;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List<Trip> trips = null;
		LOG.debug("Command starts");
		try { if(request.getSession().getAttribute("sort")!=null) {
			  if(request.getSession().getAttribute("sort").equals("byDate"))
			  { trips=DBManager.getInstance().findAllTripsByDate();
			
				}
		else if(request.getSession().getAttribute("sort").equals("byStatus")) {
			trips=DBManager.getInstance().findTripsByStatus();
			}
		else 
		{trips = DBManager.getInstance().findAllTrips();
			
		}}
		else
			trips = DBManager.getInstance().findAllTrips();
			
			LOG.trace("Found in DB: menuTripList --> " + trips);
			System.out.println(trips);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("trips", trips);
		
		Request req=new Request();
		LocalDate now = LocalDate.now();
		   LOG.trace("kek");

			req.setDateCreation(Date.valueOf(now.toString()));
			req.setDriverId(((User)request.getSession().getAttribute("user")).getId());
			request.getSession().setAttribute("req", req);
			List<String> statuses=new ArrayList<>();
			for(AutoType x:AutoType.values())
			statuses.add(x.getName());
			LOG.trace("Start MakeReq with statuses ->"+ statuses);
			request.getSession().setAttribute("auto_types", statuses);
		
		//request.setAttribute("trips", trips);
		LOG.debug("Command finished");
		return Path.PAGE_LIST_TRIPS;
	}

}
