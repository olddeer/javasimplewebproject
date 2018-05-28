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
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class ListAutosCommand  extends Command{
	private static final long serialVersionUID = -8368055648886963502L;
	 private static final Logger LOG = Logger.getLogger(  ListAutosCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager  dbm = null;
		try {
			dbm=DBManager.getInstance();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		if(request.getSession().getAttribute("nameAut")!=null){
			Auto aut2=new Auto();
			aut2.setAutoStatusId(1);
		aut2.setAutoTypeId(Integer.parseInt(((String)request.getSession().getAttribute("typeAut")).trim()));
		aut2.setName((String)request.getSession().getAttribute("nameAut"));
		request.getSession().removeAttribute("nameAut");
		
		try {
			dbm.insertAuto(aut2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(request.getSession().getAttribute("autoType")!=null){
			Auto aut=new Auto();
			aut.setAutoStatusId(Integer.parseInt(((String)request.getSession().getAttribute("autoStatus")).trim()));
		aut.setAutoTypeId(Integer.parseInt(((String)request.getSession().getAttribute("autoType")).trim()));
		aut.setName((String)request.getSession().getAttribute("name"));
		aut.setId(Integer.parseInt(((String)request.getSession().getAttribute("carId")).trim()));
		request.getSession().removeAttribute("autoType");
		LOG.trace("  "+aut.getName());
		try {
			dbm.updateFullAuto(aut);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		List<Auto> listAut=dbm.findAllAutos();
		request.getSession().setAttribute("listAut", listAut);
		return Path.PAGE_LIST_AUTO;
	}

}
