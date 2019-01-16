package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Address;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class UpdateAddressCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			Address adr=DBManager.getInstance().findAddressById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("adr", adr);
			request.getSession().setAttribute("adrId", request.getParameter("id"));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return Path.PAGE_UPDATE_ADDRESS;
	}

}
