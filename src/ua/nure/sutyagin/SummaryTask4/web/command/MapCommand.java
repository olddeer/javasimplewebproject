package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class MapCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229738759036812809L;
	private static final Logger LOG = Logger.getLogger(Controller.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			Trip trip= DBManager.getInstance().findTripById(Integer.parseInt(request.getParameter("id")));
		request.getSession().removeAttribute("id");
		LOG.trace(trip.getDestination());
		request.getSession().setAttribute("trip", trip);
		
		
		
		} catch (NumberFormatException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return Path.PAGE_MAP;
	}

}
