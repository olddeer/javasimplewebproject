package ua.nure.sutyagin.SummaryTask4.enteties;

import java.sql.Date;

public class Auto extends Entity{

	private static final long serialVersionUID = 8988168702032371463L;

	private String name;
	private int autoTypeId;
	private int autoStatusId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAutoTypeId() {
		return autoTypeId;
	}
	public void setAutoTypeId(int autoTypeId) {
		this.autoTypeId = autoTypeId;
	}
	
	public String getAutoTypeName() {
	
		return AutoType.getAutoType(this).getName();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Auto createAutot(String strin) {
		String[] string=strin.split(" ");
		Auto aut=new Auto();
		aut.name=string[0];
		aut.autoTypeId=Integer.parseInt(string[1]);
		aut.autoStatusId=Integer.parseInt(string[2]);
		return aut;
	}
	public int getAutoStatusId() {
		return autoStatusId;
	}
	public void setAutoStatusId(int autoStatusId) {
		this.autoStatusId = autoStatusId;
	}
	public String getStatusName() {
		return AutoStatus.getAutoStatus(this).getName();
	}
}
