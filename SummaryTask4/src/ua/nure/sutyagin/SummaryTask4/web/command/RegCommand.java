package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import ua.nure.sutyagin.SummartTask4.db.DBManager;
import ua.nure.sutyagin.SummaryTask4.enteties.User;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class RegCommand extends Command{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5462841130903898733L;



	private final static String MESSAGE="Hello dear client, you should verify your registration by following this link ";
	
	
	
	 public static final String DEFAULT_ENCODING = "UTF-8"; 
	    static BASE64Encoder enc = new BASE64Encoder();
	    static BASE64Decoder dec = new BASE64Decoder();

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
		
		 public static void sendEmail(
				     String aToEmailAddr,
				    String aSubject, String aBody
				  ){
			 Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			            protected PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication(
			                   "alexsut76@gmail.com", "Cnfkrth1");
			             }});
				    MimeMessage message = new MimeMessage(session);
				    try {
				      //the "from" address may be set in code, or set in the
				      //config file under "mail.from" ; here, the latter style is used
				      message.setFrom(new InternetAddress("alexsut76@gmail.com"));
				      message.addRecipient(
				        Message.RecipientType.TO, new InternetAddress(aToEmailAddr)
				      );
				      message.setSubject(aSubject);
				      message.setText(aBody);
				      Transport.send(message);
				    }
				    catch (MessagingException ex){
				      System.err.println("Cannot send email. " + ex);
				    }
				  }
	  

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			DBManager dbm=DBManager.getInstance();
			
			 User us=new User();
			us.setFirstName((String)request.getSession().getAttribute("fname"));
			us.setSecondName((String)request.getSession().getAttribute("sname"));
			us.setLogin((String)request.getSession().getAttribute("login2"));
			us.setPassword((String)request.getSession().getAttribute("passw"));
			us.setRoleId(Integer.parseInt(((String)request.getSession().getAttribute("role")).trim()));
			us.setBan(false);
			String mail=(String)request.getSession().getAttribute("mail");
			String logEnc=base64encode(us.getLogin());
			String link="http://localhost:8080/SummaryTask4/Controller?command=verify&str="+logEnc;
			sendEmail(mail,"Motor pool",MESSAGE+link);
			request.getSession().removeAttribute("login2");
				dbm.insertUser(us);
				User user2=dbm.findUserByLogin(us.getLogin());
				user2.setBan(false);
			dbm.updateUser(user2);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/login.jsp";
	}

}
