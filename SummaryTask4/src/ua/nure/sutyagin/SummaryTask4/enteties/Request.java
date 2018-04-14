package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;


import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.exception.DBException;

public class Request extends Entity{

	private static final long serialVersionUID = -6780138115125003439L;
private Date dateCreation;
	private int tripId;
	private int autoTypeId;
	private int driverId;

	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getAutoTypeId() {
		return autoTypeId;
	}
	public void setAutoTypeId(int autoTypeId) {
		this.autoTypeId = autoTypeId;
	}
	public void setAutoTypeId(String autoType) {
		int i=0;
		for(AutoType x:AutoType.values()) {
			i++;
			if(x.getName().equals(autoType)) {
				break;
			}
				
		}
		this.autoTypeId = i;
	}
	
	public String getAutoTypeName() {
		
		return AutoType.getAutoType(this).getName();
		
	}
	public String getDriverName()  {
		
		try {
			return DBManager.getInstance().findUserById(this.getDriverId()).getLogin();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Request [dateCreation=" + dateCreation + ", tripId=" + tripId + ", autoTypeId=" + autoTypeId
				+ ", driverId=" + driverId + "]";
	}
	public static Request createRequest(String strin) {
		String[] string=strin.split(" ");
		Request req=new Request();
		req.tripId=Integer.parseInt(string[0]);
		req.autoTypeId=Integer.parseInt(string[1]);
		req.driverId=Integer.parseInt(string[2]);
		req.dateCreation= Date.valueOf(string[3]);
		
		return req;
	}
	
	
}
