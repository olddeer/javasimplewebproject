package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class MakeBanCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4907494318122996778L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager db=null;
		try {
			 db=DBManager.getInstance();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getSession().getAttribute("ban")!=null) {
			User us=db.findUserById(Integer.parseInt(((String)request.getSession().getAttribute("ban")).trim()));
			us.setBan(false);
			try {
				db.updateUser(us);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(request.getSession().getAttribute("unban")!=null) {
			User us=db.findUserById(Integer.parseInt(((String)request.getSession().getAttribute("unban")).trim()));
			us.setBan(true);
			try {
				db.updateUser(us);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			request.getSession().setAttribute("listUs", db.findAllUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Path.PAGE_LIST_USERS;
	}

}
