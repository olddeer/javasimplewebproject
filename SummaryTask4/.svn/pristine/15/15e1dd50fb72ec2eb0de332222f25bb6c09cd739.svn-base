package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;


public class MakeTripCommand  extends Command{

	private static final long serialVersionUID = -5136739693972902302L;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Trip trp=new Trip();
		request.getSession().setAttribute("trip", trp);
		
		return Path.PAGE_MAKE_TRIP;
	}

}
