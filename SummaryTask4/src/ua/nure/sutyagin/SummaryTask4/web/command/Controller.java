package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

/**
 * Servlet implementation class Controller
 */

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Controller.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Its from doGet");
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Its from doPost");
		sendRedirect(request, response);
		// process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.getSession().setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		// go to forward
		// System.out.println(forward);

		request.getRequestDispatcher(forward).forward(request, response);
	}

	public void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if(request.getParameter("command").equals("setLocale")) {
			request.getSession().setAttribute("page", request.getParameter("page"));
			request.getSession().setAttribute("language", request.getParameter("language"));
			LOG.trace("page "+request.getParameter("page"));
		}
		if(request.getParameter("command").equals("deleteCar")) {
			request.getSession().setAttribute("carId", request.getParameter("id"));
			
		}
		if(request.getParameter("command").equals("deleteUser")) {
			request.getSession().setAttribute("userId", request.getParameter("id"));
			
		}
		if(request.getParameter("command").equals("updateCar")) {
			request.getSession().setAttribute("carId", request.getParameter("id"));
			
		}
		if(request.getParameter("command").equals("updateAdr")) {
			request.getSession().setAttribute("adrId", request.getParameter("id"));
			request.getSession().setAttribute("nameAdr", request.getParameter("nameAdr"));
		}
		if (request.getParameter("command").equals("login")) {
			request.getSession().setAttribute("login", request.getParameter("login"));
			request.getSession().setAttribute("password", request.getParameter("password"));
		}
		if (request.getParameter("command").equals("listTripsDisp") && request.getParameter("dest") != null) {
			request.getSession().setAttribute("dest", request.getParameter("dest"));
			request.getSession().setAttribute("from", request.getParameter("from"));
			request.getSession().setAttribute("date", request.getParameter("date"));
		}
		
		if(request.getParameter("command").equals("listAddress") && request.getParameter("nameAdr")!=null) {
			
			request.getSession().setAttribute("nameAdr",  request.getParameter("nameAdr"));
		}
if(request.getParameter("command").equals("listAddress") && request.getParameter("nameAdrC")!=null) {
			
			LOG.trace( request.getParameter("nameAdrC"));
			request.getSession().setAttribute("nameAdrC",  request.getParameter("nameAdrC"));
		}
		if(request.getParameter("command").equals("listAutos") && request.getParameter("autoStatus")!=null) {
			request.getSession().setAttribute("autoStatus", request.getParameter("autoStatus"));
			request.getSession().setAttribute("autoType",  request.getParameter("autoType"));
			request.getSession().setAttribute("name",  request.getParameter("name"));
		}
		if(request.getParameter("command").equals("listUsers") && request.getParameter("log")!=null) {
			LOG.trace(request.getParameter("log"));
			request.getSession().setAttribute("login2", request.getParameter("log"));
			request.getSession().setAttribute("passw", request.getParameter("pasw"));
			request.getSession().setAttribute("fname", request.getParameter("fname"));
			request.getSession().setAttribute("sname", request.getParameter("lname"));
				request.getSession().setAttribute("role", request.getParameter("role"));
		
		}

		if(request.getParameter("command").equals("map")) {
			LOG.trace("we get command map "+request.getParameter("id"));
			request.getSession().setAttribute("tripId", request.getParameter("id"));
			
		}
		if(request.getParameter("command").equals("listAutos") && request.getParameter("nameAut")!=null) {
			
			
			request.getSession().setAttribute("nameAut",  request.getParameter("nameAut"));

			request.getSession().setAttribute("typeAut",  request.getParameter("autoType"));
			
		}
		
		if(request.getParameter("command").equals("reg")) {
			LOG.trace("we get it " + request.getParameter("log") +"   "+request.getParameter("role"));
			request.getSession().setAttribute("login2", request.getParameter("log"));
			request.getSession().setAttribute("passw", request.getParameter("pasw"));
			request.getSession().setAttribute("fname", request.getParameter("fname"));
			request.getSession().setAttribute("sname", request.getParameter("lname"));
				request.getSession().setAttribute("mail", request.getParameter("mail"));
				request.getSession().setAttribute("role", request.getParameter("role"));
		
		}
			if(request.getParameter("command").equals("makeBan")) {
			
			request.getSession().setAttribute("ban", request.getParameter("ban"));
			request.getSession().setAttribute("unban", request.getParameter("Unban"));
			}
		
		if(request.getParameter("command").equals("listTripsDisp")) {
			
			request.getSession().setAttribute("sort", request.getParameter("sort"));
		}

		if (request.getParameter("command").equals("makeCompleteRequest") && request.getParameter("req") != null) {

			request.getSession().setAttribute("req", request.getParameter("req"));

		}
		if (request.getParameter("command").equals("listComplReq") && request.getParameter("date") != null) {

			request.getSession().setAttribute("dateCompl", request.getParameter("date"));
		}
		if (request.getParameter("command").equals("tripAccepted") && request.getParameter("reqId") != null) {

			request.getSession().setAttribute("reqId", request.getParameter("reqId"));
			LOG.trace("stats " + request.getParameter("stats"));
			request.getSession().setAttribute("stats", request.getParameter("stats"));
		}
		if (request.getParameter("command").equals("listTrips") && request.getParameter("stats") != null) {

			request.getSession().setAttribute("stats", request.getParameter("stats"));
			LOG.trace("stats " + request.getParameter("stats"));
			request.getSession().setAttribute("reqId", request.getParameter("reqId"));
			LOG.trace("command " + request.getParameter("command"));
			response.sendRedirect("/SummaryTask4/Controller?command=tripAccepted");

			return;

		}
		if(request.getParameter("command").equals("listTrips")&& request.getParameter("sort")!=null) {
			request.getSession().setAttribute("sort", request.getParameter("sort"));
			
		}
		if(request.getParameter("command").equals("makeRequest")&& request.getParameter("tripId")!=null) {
			request.getSession().setAttribute("tripId", request.getParameter("tripId"));
			request.getSession().setAttribute("autoTypeId", request.getParameter("autoTypeId"));
			
		}
		LOG.trace("command " + request.getParameter("command"));
		response.sendRedirect("/SummaryTask4/Controller?command=" + request.getParameter("command"));
	}
}
