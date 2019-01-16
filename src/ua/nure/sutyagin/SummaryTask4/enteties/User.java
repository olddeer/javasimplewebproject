package ua.nure.sutyagin.SummaryTask4.enteties;



public class User extends Entity {

	private static final long serialVersionUID = 3402538212171736301L;
private String firstName;
private String  secondName;
private int roleId;
private String login;
private String password;
private boolean ban;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getSecondName() {
	return secondName;
}
public void setSecondName(String secondName) {
	this.secondName = secondName;
}

public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public static User createUser(String str) {
	String[] string=str.split(" ");
	User us=new User();
	us.setLogin(string[0]);
	us.password=string[1];
	us.firstName=string[2];
	us.secondName=string[3];
	us.roleId=Integer.parseInt(string[4]);
	return us;
}



@Override
public String toString() {
	return "User [ id="+getId()+", "+"firstName=" + firstName + ", secondName=" + secondName  + ", roleId=" + roleId
			+ ", login=" + login + ", password=" + password + "]";
}
public boolean isBan() {
	return ban;
}
public void setBan(boolean ban) {
	this.ban = ban;
}

	
}
