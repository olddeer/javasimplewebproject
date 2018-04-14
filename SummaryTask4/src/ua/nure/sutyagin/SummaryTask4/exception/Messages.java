package ua.nure.sutyagin.SummaryTask4.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Dmytro_Kolesnikov
 *
 */
public class Messages {

	private Messages() {
		// no op
	}
	
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = "Cannot obtain user order beans";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_CATEGORIES = "Cannot obtain categories";

	public static final String ERR_CANNOT_OBTAIN_MENU_ITEMS = "Cannot obtain menu items";

	public static final String ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER = "Cannot obtain menu items by order";

	public static final String ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS = "Cannot obtain menu items by its identifiers";

	public static final String ERR_CANNOT_OBTAIN_ORDERS = "Cannot obtain orders";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID = "Cannot obtain orders by status id";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_IDENTIFIERS = "Cannot obtain orders by its identifiers";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_USER_AND_STATUS_ID = "Cannot obtain orders by user and status id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	public static final String ERR_CANNOT_OBTAIN_ALL_AUTOS = "CANNOT OBTAIN ALL AUTOS";

	public static final String ERR_CANNOT_OBTAIN_ALL_TRIPS_BY_STATUS = "CANNOT OBTAIN ALL TRIPS BY STATUS";

	public static final String ERR_CANNOT_OBTAIN_TRIP_BY_ID = "CANNOT OBTAIN TRIP BY ID";

	public static final String ERR_CANNOT_OBTAIN_REQ_BY_ID = "CANNOT OBTAIN REQUEST BY ID";

	public static final String ERR_CANNOT_OBTAIN_INSERT_USER = "CANNOT OBTAIN INSERT USER";

	public static final String ERR_CANNOT_OBTAIN_FIND_USER_BY_LOGIN = "CANNOT OBTAIN FIND USER BY LOGIN";
	
}