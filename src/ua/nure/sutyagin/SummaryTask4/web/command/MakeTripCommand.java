package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Address;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;


public class MakeTripCommand  extends Command{

	private static final long serialVersionUID = -5136739693972902302L;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Address> listAdr=new ArrayList<>();
		try {
			listAdr = DBManager.getInstance().findAllAdr();
			request.setAttribute("listAdr",  listAdr);
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
		
		return Path.PAGE_MAKE_TRIP;
	}

}
