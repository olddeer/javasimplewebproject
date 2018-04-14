package ua.nure.sutyagin.SummaryTask4.enteties;

public enum AutoType {
 TRUCK,VAN,SEDAN;
 public static AutoType getAutoType(Request req) {
		int roleId = req.getAutoTypeId();
		
		return AutoType.values()[roleId-1];
	}
 public static AutoType getAutoType(Auto auto) {
		int roleId = auto.getAutoTypeId();
		
		return AutoType.values()[roleId-1];
	}
 public int getId() {
	 int i = 1;
		for (AutoType x : AutoType.values()) {
			if (x.equals(this)) {
				break;
			}
			i++;
		}
		return i;
	 
 }
	public String getName() {
		return name().toLowerCase();
	}
}
