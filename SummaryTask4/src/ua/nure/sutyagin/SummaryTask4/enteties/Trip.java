package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;

import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;


public class Trip extends Entity{
	private static final long serialVersionUID = 770625403563895111L;
private Date dateCreation;
private int statusId;	
private int destination_id;

public int getDestination_id() {
	return destination_id;
}
public void setDestination_id(int destination_id) {
	this.destination_id = destination_id;
}
public int getFrom_id() {
	return from_id;
}
public void setFrom_id(int from_id) {
	this.from_id = from_id;
}
private Date dateSetOff;
private int from_id;
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

public Date getDateSetOff() {
	return dateSetOff;
}
public void setDateSetOff(Date dateSetOff) {
	this.dateSetOff = dateSetOff;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}


@Override
public String toString() {
	return "Trip [dateCreation=" + dateCreation + ", statusId=" + statusId + ", destination=" + destination_id
			+ ", dateSetOff=" + dateSetOff + ", from=" + from_id  + "]";
}
public int getDispatcher_id() {
	return dispatcher_id;
}
public void setDispatcher_id(int dispatcher_id) {
	this.dispatcher_id = dispatcher_id;
}

public String getDestination() {
	String name="NOT FOUND";
	try {
		name = DBManager.getInstance().findAddressById(destination_id).getName();
	} catch (DBException | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
	return name.trim();
}

public String getFrom() {
	String name="NOT FOUND";
	try {
		name = DBManager.getInstance().findAddressById(from_id).getName();
	} catch (DBException | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
	return name;
	
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
 