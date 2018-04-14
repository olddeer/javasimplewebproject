package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;


import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;

public class CompletedRequest extends Entity {
/**
	 * 
	 */
	private static final long serialVersionUID = 1422609050206053553L;
private int id;
private int request_id;
private int auto_id;
private Date dateCompleted;
@Override
public String toString() {
	return "CompletedRequest [id=" + id + ", request_id=" + request_id + ", auto_id=" + auto_id + ", dateCompleted="
			+ dateCompleted + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRequest_id() {
	return request_id;
}
public void setRequest_id(int request_id) {
	this.request_id = request_id;
}
public int getAuto_id() {
	return auto_id;
}
public void setAuto_id(int auto_id) {
	this.auto_id = auto_id;
}
public Date getDateCompleted() {
	return dateCompleted;
}
public void setDateCompleted(Date dateCompleted) {
	this.dateCompleted = dateCompleted;
}
public int getTripId() {
	Request req =new Request();
	try {
		 req=DBManager.getInstance().findReqById(request_id);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return req.getTripId();
}

public String getStatusTrip() {
	Trip trp=getTrip();
	return Status.values()[trp.getStatusId()-1].getName();
}
public Trip getTrip() {
	Request req =new Request();
	Trip trp=new Trip();
	try {
		 req=DBManager.getInstance().findReqById(request_id);
		 trp=DBManager.getInstance().findTripById(req.getTripId());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return trp;
}

public String getDestination() {
	return getTrip().getDestination();
}


public String getFrom() {
	return getTrip().getFrom();
}

public String getDispatcherLogin() {
	Trip trp;
	try {
		trp = DBManager.getInstance().findTripById(this.getTripId());
		User us=DBManager.getInstance().findUserById(trp.getDispatcher_id());
		return us.getLogin();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public String getDriverLogin() {
	Request req =new Request();
	String login=null;
	try {
		 req=DBManager.getInstance().findReqById(request_id);
		 login=req.getDriverName();
	} catch (ClassNotFoundException  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return login;
	
}
public String getAutoName() {
	String autoName=null;
	try {
		Auto auto=DBManager.getInstance().findAutoById(auto_id);
		autoName=auto.getName();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return autoName;
	
}
public String getAutoTypeName() {
	String typeName=null;
	try {
		Auto auto=DBManager.getInstance().findAutoById(auto_id);
		typeName=AutoType.values()[auto.getAutoTypeId()-1].getName();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return typeName;
	
}
public static CompletedRequest createCompletedRequest(String strin) {
	String[] string=strin.split(" ");
	CompletedRequest req=new CompletedRequest();
		req.id=Integer.parseInt(string[0]);
		req.request_id=Integer.parseInt(string[1]);
		req.auto_id=Integer.parseInt(string[2]);
		req.dateCompleted=Date.valueOf(string[3]);
	return req;
}
}
