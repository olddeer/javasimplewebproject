package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoStatus;
import ua.nure.sutyagin.SummaryTask4.enteties.CompletedRequest;
import ua.nure.sutyagin.SummaryTask4.enteties.Role;

public class ListComplReqCommand extends Command {

	private static final long serialVersionUID = -2277441354749662986L;
	 private static final Logger LOG = Logger.getLogger(  ListComplReqCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (request.getSession().getAttribute("dateCompl") != null) {
			CompletedRequest cr = (CompletedRequest) request.getSession().getAttribute("cr");
			SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
			LOG.trace(cr);
			Date dt;
			
			try {
				dt = new Date(format.parse((String)request.getSession().getAttribute("dateCompl")).getTime());
				cr.setDateCompleted(dt);
				DBManager.getInstance().insertCompletedRequest(cr);
				request.getSession().removeAttribute("dateCompl");
			} catch (ParseException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		List<CompletedRequest> listComplReq=null;
		
		try {
			if (((Role) request.getSession().getAttribute("userRole")).getName().equals( "dispatcher") ||((Role) request.getSession().getAttribute("userRole")).getName().equals( "administrator") ) {
				
				listComplReq=DBManager.getInstance().findAllCompReq();
				request.getSession().setAttribute("complReq", new CompletedRequest());
				LOG.trace(" dips reqs "+listComplReq);
			} else
				{
				List<AutoStatus> listStatAut=DBManager.getInstance().findAllAutoStatuses();
				listComplReq = DBManager.getInstance().findAllComplRequestsByDriverID((Integer)request.getSession().getAttribute("userId"));
				request.getSession().setAttribute("autoStats", listStatAut);
				request.getSession().setAttribute("complReq", new CompletedRequest());
				/*int reqId = 0;
				request.getSession().setAttribute("complReqId", reqId);*/
				LOG.trace("driver");
				}
			LOG.trace("reqs "+listComplReq);
			request.getSession().setAttribute("listComplReq", listComplReq);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Path.PAGE_LIST_COMPL_REQ;
	}

}
