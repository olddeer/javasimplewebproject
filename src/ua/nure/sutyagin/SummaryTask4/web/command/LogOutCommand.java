package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class LogOutCommand extends Command{

	private static final Logger LOG = Logger.getLogger(LogOutCommand.class);
	private static final long serialVersionUID = -3507977595128323270L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
		
	}

}
