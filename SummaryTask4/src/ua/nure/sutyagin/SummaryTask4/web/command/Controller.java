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
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		// go to forward
		// System.out.println(forward);

		request.getRequestDispatcher(forward).forward(request, response);
	}

	public void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("command").equals("login")) {
			request.getSession().setAttribute("login", request.getParameter("login"));
			request.getSession().setAttribute("password", request.getParameter("password"));
		}
		if (request.getParameter("command").equals("listTripsDisp") && request.getParameter("dest") != null) {
			request.getSession().setAttribute("dest", request.getParameter("dest"));
			request.getSession().setAttribute("from", request.getParameter("from"));
			request.getSession().setAttribute("date", request.getParameter("date"));

		}
		if(request.getParameter("command").equals("listUsers") && request.getParameter("log")!=null) {
			
			request.getSession().setAttribute("login", request.getParameter("log"));
			request.getSession().setAttribute("passw", request.getParameter("pasw"));
			request.getSession().setAttribute("fname", request.getParameter("fname"));
			request.getSession().setAttribute("sname", request.getParameter("lname"));
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
