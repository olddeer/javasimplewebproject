package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.Broken_Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.CompletedRequest;
import ua.nure.sutyagin.SummaryTask4.enteties.Trip;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;


public class TripAcceptedCommand extends Command {
	private static final long serialVersionUID = -2333467645200782020L;
	 private static final Logger LOG = Logger.getLogger(TripAcceptedCommand .class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		if((String)request.getSession().getAttribute("reqId")!=null) {
		int reqId=Integer.parseInt((String)request.getSession().getAttribute("reqId"));
		LOG.trace("Complete request id "+reqId);
		request.getSession().removeAttribute("reqId");
		CompletedRequest rq=null;
		try {int i=Integer.parseInt((String)request.getSession().getAttribute("stats"));
		request.getSession().removeAttribute("stats");
		LOG.trace("Stats id "+i);
			DBManager dbm= DBManager.getInstance();
			rq=dbm.findAllComplRequestsById(reqId);
			int rq2Id=rq.getRequest_id();
			Trip trp= dbm.findTripById(dbm.findReqById(rq2Id).getTripId());
			trp.setStatusId(1);
			dbm.updateTrip(trp);
			Broken_Auto ba=new Broken_Auto();
			Auto auto=dbm.findAutoById(rq.getAuto_id());
			
			auto.setAutoStatusId(i);
			if(i==2 || i==3) {
				
				ba.setAutoId(auto.getId());
				ba.setDateOfBroken(rq.getDateCompleted());
				dbm.insertBroken_Auto(ba);
				}
			request.getSession().removeAttribute("stats");
			dbm.updateAuto(auto);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return Path.PAGE_LIST_TRIPS;
	}

}
