package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class ListAutosCommand  extends Command{
	private static final long serialVersionUID = -8368055648886963502L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager  dbm = null;
		try {
			dbm=DBManager.getInstance();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		List<Auto> listAut=dbm.findAllAutos();
		request.getSession().setAttribute("listAut", listAut);
		return Path.PAGE_LIST_AUTO;
	}

}
