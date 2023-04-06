package com.customer.reward.rewardservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateTimeUtils {
	
	private static List<Integer> fieldsToReset = Arrays.asList(
			Calendar.MILLISECOND, 
			Calendar.SECOND, 
			Calendar.MINUTE, 
			Calendar.HOUR_OF_DAY);
	static SimpleDateFormat reportDate = new SimpleDateFormat("yyyy-MM-dd");

	
	public static Date roundDownToStartOfDay (Date queryDate) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(queryDate);			
			fieldsToReset.forEach(field -> cal.set(field, 0));
			return cal.getTime();		
	}
	
	public static Date parseDate(String date)throws ParseException {
		return reportDate.parse(date);	
	}
	
	public static Date addDays(Date date,int noOfDays,String timezone) {
		Calendar cal = Calendar.getInstance();
		//cal.setTimeZone(TimeZone.getTimeZone(timezone));	
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, noOfDays);
		return roundDownToStartOfDay(cal.getTime());
	}
	
	
	

}
