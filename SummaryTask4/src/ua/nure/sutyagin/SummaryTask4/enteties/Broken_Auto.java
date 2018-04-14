package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;

import ua.nure.sutyagin.SummartTask4.db.DBManager;

public class Broken_Auto extends Entity{


	private static final long serialVersionUID = -6896434072596201412L;
	private int id;
	private Date dateOfBroken;
	private int autoId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAutoName() {
		Auto car=null;
		try {
			car = DBManager.getInstance().findAutoById(autoId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return car.getName(); 
	}
	public Date getDateOfBroken() {
		return dateOfBroken;
	}
	public void setDateOfBroken(Date dateOfBroken) {
		this.dateOfBroken = dateOfBroken;
	}
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
}
