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

public class ListAddressCommand extends Command {
	private static final Logger LOG = Logger.getLogger(Controller.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787667166826047860L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
	
		if(request.getSession().getAttribute("nameAdrC")!=null) {
			Address adr=new Address();
			LOG.trace("kek"+(String)request.getSession().getAttribute("nameAdrC"));
			adr.setName(new String(((String)request.getSession().getAttribute("nameAdrC")).getBytes("UTF-8")));
			try {
				DBManager.getInstance().insertAddress(adr);
			} catch (ClassNotFoundException e) {
								e.printStackTrace();
			}
			
		request.getSession().removeAttribute("nameAdrC");
		}
		if(request.getSession().getAttribute("nameAdr")!=null) {
			Address adr=new Address();
			adr.setId(Integer.parseInt((String)request.getSession().getAttribute("adrId")));
			adr.setName((String)request.getSession().getAttribute("nameAdr"));
			try {
				DBManager.getInstance().updateAddress(adr);
			} catch (ClassNotFoundException e) {
								e.printStackTrace();
			}
			
			
			request.getSession().removeAttribute("nameAdr");
		}
		try {
		List<Address> list=DBManager.getInstance().findAllAdr();
		request.setAttribute("listAdr", list);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return Path.PAGE_LIST_ADDRESS;
	}

}
