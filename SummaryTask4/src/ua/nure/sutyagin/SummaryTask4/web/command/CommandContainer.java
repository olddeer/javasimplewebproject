package ua.nure.sutyagin.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import org.apache.log4j.Logger;





public class CommandContainer {
	private static final Logger LOG = Logger.getLogger( CommandContainer.class);


	private static Map<String, Command> commands = new TreeMap<String, Command>();
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("listTrips", new ListTripsCommand());
		commands.put("listTripsDisp", new ListTripsDispCommand());
		commands.put("makeRequest", new MakeRequestCommand());
		commands.put("completeRequest", new CompleteRequestCommand());
		commands.put("makeTrip", new MakeTripCommand());
		commands.put("makeCompleteRequest", new MakeCompleteRequestCommand());
		commands.put("seeRequests", new SeeRequestsCommand());
		commands.put("listComplReq", new ListComplReqCommand());
		commands.put("listAutos",new ListAutosCommand());
		commands.put("tripAccepted", new TripAcceptedCommand());
		commands.put("logout", new LogOutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("listUsers", new ListUsersCommand());
		commands.put("makeBan", new MakeBanCommand());
		commands.put("regNewUser", new MakeUserCommand());
		commands.put("deleteCar", new DeleteAutoCommand());
		commands.put("selectUpdateCar", new SelectCarUpdateCommand());
		commands.put("setLocale", new SetLocaleCommand());
		commands.put("map", new MapCommand());
		commands.put("deleteUser", new DeleteUserCommand());
		commands.put("addAuto", new AddAutoCommand());
		commands.put("listAddress", new ListAddressCommand());
		commands.put("addAddress", new AddAddressCommand());
		commands.put("deleteAdr", new DeleteAddressCommand());
		commands.put("updateAdr",new UpdateAddressCommand());
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}

}
