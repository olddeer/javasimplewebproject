package ua.nure.sutyagin.SummaryTask4.enteties;

public enum Status {
	ACCEPT, REJECT, OPEN, INPROGRESS;
	public static Status getStatus(Trip trip) {
		int roleId = trip.getStatusId();
		return Status.values()[roleId-1];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
