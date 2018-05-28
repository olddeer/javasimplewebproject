package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class VerifyMailCommand extends Command {
    static BASE64Encoder enc = new BASE64Encoder();
    static BASE64Decoder dec = new BASE64Decoder();
	 public static final String DEFAULT_ENCODING = "UTF-8"; 
    public static String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }//base64encode

    public static String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }//base64decod
	/**
	 * 
	 */
	private static final long serialVersionUID = -539992962032312306L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			DBManager db=DBManager.getInstance();
			List<User> lu=db.findAllUsers();
			String linkDec=(String)request.getSession().getAttribute("link");
			String login= base64decode(linkDec);
			for(User x: lu){
				if(x.getLogin().equals(login))
				{
					x.setBan(true);
					db.updateUser(x);
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
