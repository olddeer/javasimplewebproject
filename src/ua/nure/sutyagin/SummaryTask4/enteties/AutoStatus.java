package ua.nure.sutyagin.SummaryTask4.enteties;

public enum AutoStatus {
	READY, BUSY, FALLENOUT;
	public static AutoStatus getAutoStatus(Auto auto) {
		int autoStatusId = auto.getAutoStatusId();
		return AutoStatus.values()[autoStatusId - 1];
	}

	public String getName() {
		return name().toLowerCase();
	}

	public int getId() {
		int i = 1;
		for (AutoStatus x : AutoStatus.values()) {
			if (x.equals(this)) {
				break;
			}
			i++;
		}
		return i;
	}
}
