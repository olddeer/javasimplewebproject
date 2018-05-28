package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoType;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class SelectCarUpdateCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8108742779789175793L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			Auto aut=DBManager.getInstance().findAutoById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("auto", aut);
			request.getSession().setAttribute("carId", request.getParameter("id"));
		} catch (NumberFormatException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return Path.PAGE_UPDATE_AUTO;
	}

}
