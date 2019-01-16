package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.Auto;
import ua.nure.sutyagin.SummaryTask4.enteties.Role;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class ListUsersCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager  dbm = null;
		try {
			dbm=DBManager.getInstance();
			if(request.getSession().getAttribute("login2")!=null) 
			{ User us=new User();
			
			
			us.setFirstName(((String)request.getSession().getAttribute("fname")).trim());
			us.setSecondName(((String)request.getSession().getAttribute("sname")).trim());
			us.setLogin(((String)request.getSession().getAttribute("login2")).trim());
			us.setPassword((String)request.getSession().getAttribute("passw"));
			us.setRoleId(Integer.parseInt(((String)request.getSession().getAttribute("role")).trim()));
			us.setBan(true);
			request.getSession().removeAttribute("login2");
				String insertMssg=dbm.insertUser(us);
				if(insertMssg!=null) {
				if(insertMssg.equals("Login is required")) {
						
					request.setAttribute("logInfo", insertMssg);
						//request.getSession().setAttribute("logInfo", insertMssg);
						Role[] roles=Role.values();
						request.getSession().setAttribute("roles", roles);
						return Path.PAGE_MAKE_USER;
				}}
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		List<User> listUs;
		try {
			listUs = dbm.findAllUsers();
		
			request.getSession().setAttribute("listUs", listUs);
			return Path.PAGE_LIST_USERS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
