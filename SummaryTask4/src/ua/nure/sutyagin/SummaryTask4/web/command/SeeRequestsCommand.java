package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.enteties.User;

public class SeeRequestsCommand extends Command{

	private static final long serialVersionUID = 3494643786699614095L;
	 private static final Logger LOG = Logger.getLogger(SeeRequestsCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Request> listReq=null;
		try {
			if(((User)request.getSession().getAttribute("user")).getRoleId()==3 || ((User)request.getSession().getAttribute("user")).getRoleId()==1)
			 listReq=DBManager.getInstance().findAllRequestsByStatusId(3,((User)request.getSession().getAttribute("user")).getId());
			else 
			listReq=DBManager.getInstance().findAllRequestsByStatusId(4);
			 
			LOG.trace("Get list "+ listReq);
			LOG.trace("Get list "+ listReq);
			request.getSession().setAttribute("listReqDisp", listReq);
			LOG.trace("command "+request.getParameter("command"));
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return Path.PAGE_LIST_REQUESTS;
	}

}
