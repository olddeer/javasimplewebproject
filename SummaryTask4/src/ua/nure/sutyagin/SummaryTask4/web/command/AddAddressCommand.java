package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class AddAddressCommand extends Command{

	
	private static final long serialVersionUID = -6922030124228340843L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		return Path.PAGE_ADD_ADDRESS;
	}

}
