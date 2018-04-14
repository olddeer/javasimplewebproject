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
import ua.nure.sutyagin.SummaryTask4.enteties.Broken_Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.CompletedRequest;
import ua.nure.sutyagin.SummaryTask4.enteties.Request;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class MakeCompleteRequestCommand extends Command {

	private static final long serialVersionUID = -2866369883814963585L;
	 private static final Logger LOG = Logger.getLogger( MakeCompleteRequestCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.trace("Command starts");
		int reqId=Integer.parseInt((String)request.getSession().getAttribute("req"));
		try {
			Request req=DBManager.getInstance().findReqById(reqId);
			CompletedRequest cr=new CompletedRequest();
			List<Broken_Auto> brokenAutos=DBManager.getInstance().findAllBrokenCarsByType(req.getAutoTypeId());
		LOG.trace("get brokens "+ brokenAutos);
			cr.setRequest_id(reqId);
			List <Auto> autos=DBManager.getInstance().findAllAuto(req.getAutoTypeId());
			request.getSession().setAttribute("crReq", req);
			request.getSession().setAttribute("cr",cr);
			LOG.trace("AutoTypeName "+req.getAutoTypeName());
			request.getSession().setAttribute("autoTypeName", req.getAutoTypeName());
			request.getSession().setAttribute("autos",autos);
			request.getSession().setAttribute("brokenAutos", brokenAutos);
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
		
		return Path.PAGE_MAKE_COMP_REQUEST;
	}

}
