package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class SetLocaleCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966713349388956400L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
			return (String)request.getSession().getAttribute("page");
	}

}
