package com.up.tx.manager.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	
	public static String formateDate(ZonedDateTime date, String dateFormat) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String formattedString = date.format(formatter);
		
		return formattedString;
	}
	
}
