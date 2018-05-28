package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Role;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class MakeUserCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8016710933747810942L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		Role[] roles=Role.values();
		request.getSession().setAttribute("roles", roles);
		return Path.PAGE_MAKE_USER;
	}

}
