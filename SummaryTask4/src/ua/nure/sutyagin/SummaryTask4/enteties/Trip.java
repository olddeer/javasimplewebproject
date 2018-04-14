package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;


public class Trip extends Entity{
	private static final long serialVersionUID = 770625403563895111L;
private Date dateCreation;
private int statusId;	
private String destination;
private Date dateSetOff;
private String from;
private int dispatcher_id;

public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public int getStatusId() {
	return statusId;
}
public void setStatusId(int statusId) {
	this.statusId = statusId;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public Date getDateSetOff() {
	return dateSetOff;
}
public void setDateSetOff(Date dateSetOff) {
	this.dateSetOff = dateSetOff;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public static Trip createTrip(String strin) {
	String[] string=strin.split(" ");
	Trip trip=new Trip();
	trip.statusId=Integer.parseInt(string[0]);
	trip.from=string[1];
	trip.dateCreation= Date.valueOf(string[2]);
	trip.dateSetOff=Date.valueOf(string[3]);
	trip.dispatcher_id=Integer.parseInt(string[4]);
	
	return trip;
}


@Override
public String toString() {
	return "Trip [dateCreation=" + dateCreation + ", statusId=" + statusId + ", destination=" + destination
			+ ", dateSetOff=" + dateSetOff + ", from=" + from  + "]";
}
public int getDispatcher_id() {
	return dispatcher_id;
}
public void setDispatcher_id(int dispatcher_id) {
	this.dispatcher_id = dispatcher_id;
}

public String getStatusName() {
	return Status.getStatus(this).getName();
}
public String getDispatcherName() {
	
	try {
		return DBManager.getInstance().findUserById(this.dispatcher_id).getFirstName();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
 