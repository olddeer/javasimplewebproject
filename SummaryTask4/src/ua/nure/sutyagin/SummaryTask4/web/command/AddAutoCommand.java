package ua.nure.sutyagin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sutyagin.SummaryTask4.Path;
import ua.nure.sutyagin.SummaryTask4.enteties.AutoType;
import ua.nure.sutyagin.SummaryTask4.exception.AppException;

public class AddAutoCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List<AutoType> list=Arrays.asList(AutoType.values());
		request.setAttribute("listAut", list);
		return Path.PAGE_ADD_AUTO;
	}

}
