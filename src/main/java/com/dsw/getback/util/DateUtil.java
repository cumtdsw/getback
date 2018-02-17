package com.dsw.getback.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 日期相减，得出相差几小时
	 * 
	 * @param newDate
	 * @param oldDate
	 * @return
	 */
	public static double dateSubtraction(Date newDate, Date oldDate) {
		long newTime = newDate.getTime();
		long oldTime = oldDate.getTime();
		double betweenDate = ((newTime - oldTime) * 1.0) / (1000 * 60 * 60); // 计算间隔多少天，则除以毫秒到天的转换公式
		return betweenDate;
	}
	
	public static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static Date format(Date date) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(sdf.format(date));
		return sdf.parse(sdf.format(date));
	}
	
	public static void main(String[] args) throws ParseException, InterruptedException {
		Date d1 = new Date();
		
		System.out.println(d1);
		Thread.sleep(10000);
		Date d2 = new Date();
		System.out.println(d2);
		double ab = dateSubtraction(d2,d1);
		System.out.println(ab);
	}
	
}
