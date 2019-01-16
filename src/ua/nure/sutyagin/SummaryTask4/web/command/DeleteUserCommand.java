package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class DeleteUserCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
	try {
		DBManager.getInstance().deleteUser((Integer.parseInt(request.getParameter("id"))));
	} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
	
		e.printStackTrace();
	}
		return Path.PAGE_LIST_USERS;
	}

}
