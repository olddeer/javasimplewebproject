package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Address;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class DeleteAddressCommand  extends Command{

	/**
	 * 
	 */private static final Logger LOG = Logger.getLogger(DBManager.class);
	private static final long serialVersionUID = -5348200479753551541L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			LOG.trace(id);
			DBManager.getInstance().deleteAddress(id);
			
			List<Address> list=DBManager.getInstance().findAllAdr();
			request.setAttribute("listAdr", list);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Path.PAGE_LIST_ADDRESS;
	}

}
