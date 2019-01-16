package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;

public class CompleteRequestCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4728998691770750826L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Request req=(Request)request.getSession().getAttribute("req");	
		System.out.println(req);
		DBManager db;
		try {
			db = DBManager.getInstance();
			db.insertRequset(req);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Path.PAGE_LIST_COMPL_REQ;
	}

}
