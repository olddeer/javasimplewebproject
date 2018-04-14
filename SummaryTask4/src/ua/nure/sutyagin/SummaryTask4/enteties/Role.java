package ua.nure.sutyagin.SummaryTask4.enteties;


public enum Role {
ADMINISTRATOR,DRIVER,DISPATCHER;
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		System.out.println(roleId);
		return Role.values()[roleId-1];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
