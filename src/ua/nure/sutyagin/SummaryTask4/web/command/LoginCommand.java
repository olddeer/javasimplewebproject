package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Role;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;




public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	 private static final Logger LOG = Logger.getLogger( LoginCommand.class);
		

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, AppException {
		
		
		HttpSession session = request.getSession();

		// obtain login and password from a request
		DBManager manager = null;
		try {
			manager = DBManager.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String login = (String) request.getSession().getAttribute("login");
     LOG.trace("Request parameter: loging --> " + login);

		String password = (String) request.getSession().getAttribute("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}

		User user = null;
		user = manager.findUserByLogin(login);
	LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Cannot find user with such login/password");
		}
if(!user.isBan()) {
	throw new AppException("Sorry, you was banned");
	
	
}
		Role userRole = Role.getRole(user);
		
    LOG.trace("userRole --> " + userRole);
		
		String forward = Path.PAGE_ERROR_PAGE;
			
		if (userRole == Role.ADMINISTRATOR) {
			forward = Path.COMMAND_LIST_TRIPS_DISP;
		}

		if (userRole == Role.DRIVER) {
			forward = Path.COMMAND_LIST_TRIPS;
		}
		
		if (userRole == Role.DISPATCHER) {
			forward = Path.COMMAND_LIST_TRIPS_DISP;
		}
		session.setAttribute("user", user);
	LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		session.setAttribute("userId", user.getId());
		LOG.trace("Set the session attribute: userRole --> " + userRole);
		LOG.trace("Forward to " + forward);

		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return forward;
	}

}