package me.Un1ted.Pancakes.API;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeAPI {
	public static String getCurrentTimeStamp() {
		final DateFormat dateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());
 
	}
}
