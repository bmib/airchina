/**  
 * @title DateTimeUtil.java
 * @package com.mybase.util
 * @date 2012-3-1 下午04:13:27
 */ 
package com.mybase.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理工具类
 * @author Mr.liuyong
 */
public class DateTimeUtil{
	
	private DateTimeUtil(){}
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String DATE_TIME_FORMART_ALL = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_TIME_FORMART_19 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH
	 */
	public static final String DATE_TIME_FORMART_13 = "yyyy-MM-dd HH";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_TIME_FORMART_10 = "yyyy-MM-dd";
	/**
	 * yyyy-MM
	 */
	public static final String DATE_TIME_FORMART_7 = "yyyy-MM";
	
	
	/**
	 * 获取当前日期Date类型
	 * @return Date
	 */
	public static Date getCurDate(){
		return new Date();
	}
	
	/**
	 * 返回Timestamp
	 * @return Timestamp
	 */
	public static Timestamp getCurTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 返回当前日期 java.sql.Date
	 * @return java.sql.Date
	 */
	public static Date getCurSqlDate() {
		return new java.sql.Date(getCurDate().getTime());
	}
	
	/**
	 * 将Date转换为Timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp getTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 将(yyyy-MM-dd HH:mm:ss.SSS)格式的字符串转换为Date实例
	 * @param date
	 * @return Date
	 */
	public static Date getDateByALL(String date) {
		return getDate(0,date);
	}
	
	/**
	 * 将(yyyy-MM-dd HH:mm:ss)格式的字符串转换为Date实例
	 * @param date
	 * @return Date
	 */
	public static Date getDateBy19(String date) {
		return getDate(19,date);
	}
	
	/**
	 * 将(yyyy-MM-dd HH)格式的字符串转换为Date实例
	 * @param date
	 * @return Date
	 */
	public static Date getDateBy13(String date) {
		return getDate(13,date);
	}
	
	/**
	 * 将(yyyy-MM-dd)格式的字符串转换为Date实例
	 * @param date
	 * @return Date
	 */
	public static Date getDateBy10(String date) {
		return getDate(10,date);
	}

	/**
	 * 将(yyyy-MM)格式的字符串转换为Date实例
	 * @param date
	 * @return Date
	 */
	public static Date getDateBy7(String date) {
		return getDate(7,date);
	}
	
	/**
	 * 将String转换为Date
	 * @param type
	 * @param date
	 * @return Date
	 */
	private static Date getDate(int type,String date) {
		Date result = null;
		try {
			if (date != null || !"".equals(date)) {
				String formatStr="";
				if(type==19){
					formatStr=DATE_TIME_FORMART_19;
				}else if(type==13){
					formatStr=DATE_TIME_FORMART_13;
				}else if(type==10){
					formatStr=DATE_TIME_FORMART_10;
				}else if(type==7){
					formatStr=DATE_TIME_FORMART_7;
				}else{
					formatStr=DATE_TIME_FORMART_ALL;
				}
				SimpleDateFormat formatDate = new SimpleDateFormat(formatStr, Locale.SIMPLIFIED_CHINESE);
				result = formatDate.parse(date);
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * 将Date实例转换为(yyyy-MM-dd HH:mm:ss.SSS)格式的字符串
	 * @param date
	 * @return String
	 */
	public static String getSDateByALL(Date date) {
		return getSDate(0,date);
	}
	
	/**
	 * 获取当前时间，并转成yyyy-MM-dd HH:mm:ss.SSS
	 * @return String
	 */
	public static String getSDateByALL() {
		return getSDate(0,new Date());
	}
	
	/**
	 * 将Date实例转换为(yyyy-MM-dd HH:mm:ss)格式的字符串
	 * @param date
	 * @return String
	 */
	public static String getSDateBy19(Date date) {
		return getSDate(19,date);
	}
	
	/**
	 * 获取当前时间，并转成yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getSDateBy19() {
		return getSDate(19,new Date());
	}
	
	/**
	 * 将Date实例转换为(yyyy-MM-dd HH)格式的字符串
	 * @param date
	 * @return String
	 */
	public static String getSDateBy13(Date date) {
		return getSDate(13,date);
	}
	
	/**
	 * 获取当前时间，并转成yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getSDateBy13() {
		return getSDate(13,new Date());
	}
	
	/**
	 * 将Date实例转换为(yyyy-MM-dd)格式的字符串
	 * @param date
	 * @return String
	 */
	public static String getSDateBy10(Date date) {
		return getSDate(10,date);
	}
	
	/**
	 * 获取当前时间，并转成yyyy-MM-dd
	 * @return String
	 */
	public static String getSDateBy10() {
		return getSDate(10,new Date());
	}

	/**
	 * 将Date实例转换为(yyyy-MM)格式的字符串
	 * @param date
	 * @return String
	 */
	public static String getSDateBy7(Date date) {
		return getSDate(7,date);
	}
	
	/**
	 * 获取当前时间，并转成yyyy-MM
	 * @return String
	 */
	public static String getSDateBy7() {
		return getSDate(7,new Date());
	}
	
	/**
	 * 将Date转换为指定格式的String
	 * @param type
	 * @param date
	 * @return String
	 */
	private static String getSDate(int type,Date date) {
		String result = null;
		try {
			if (date != null) {
				String formatStr="";
				if(type==19){
					formatStr=DATE_TIME_FORMART_19;
				}else if(type==13){
					formatStr=DATE_TIME_FORMART_13;
				}else if(type==10){
					formatStr=DATE_TIME_FORMART_10;
				}else if(type==7){
					formatStr=DATE_TIME_FORMART_7;
				}else{
					formatStr=DATE_TIME_FORMART_ALL;
				}
				SimpleDateFormat formatDate = new SimpleDateFormat(formatStr, Locale.SIMPLIFIED_CHINESE);
				result = formatDate.format(date);
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * 按指定格式格式化日期
	 * @param format
	 * @param date
	 * @return String
	 */
	public static String getSDate(String format,Date date){
		String result = null;
		try {
			if (date != null) {
				String formatStr=format;
				SimpleDateFormat formatDate = new SimpleDateFormat(formatStr, Locale.SIMPLIFIED_CHINESE);
				result = formatDate.format(date);
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * 按指定格式获取当天格式化日期
	 * @param format
	 * @return String
	 */
	public static String getSDate(String format){
		String result = null;
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
			result = formatDate.format(new Date());
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * Date转换为java.util.Calendar
	 * @param date
	 * @return Calendar
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = null;
		if (date != null) {
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		return calendar;
	}
	
	/**
	 * 得到星期1-7对应周一-周日
	 * @param date
	 * @return 字符串1，2，3，4，5，6，7
	 */
	public static int getWeek(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekNum == 0) {
			weekNum = 7;
		}
		return weekNum;
	}
	
	/**
	 * 得到星期1-7对应周一-周日
	 * @param date
	 * @return 字符串1，2，3，4，5，6，7
	 */
	public static String  getWeekOfChinese(Date date) {
		String weekday[]={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		if (weekNum == 0) {
			weekNum = 7;
		}
		weekNum--;
		if(weekNum>=0&&weekNum<=6)
			return weekday[weekNum];
	    return "";
	}
	
	/**
	 * 得到某年某月的天数
	 * @param year
	 * @param month
	 * @return int
	 */
	public static int getDaysOfMonth(int year,int month){
		Calendar time=Calendar.getInstance(); 
	    time.clear();
	    time.set(Calendar.YEAR,year); 
	    time.set(Calendar.MONTH,month-1);//注意,Calendar对象默认一月为0 
	    int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数 
	    return day;
	}
	
	/**
	 * 得到几天前的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
	   return now.getTime(); 
	}  
	
	/**
	 * 得到几天后的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
	   return now.getTime();  
	} 
	

}
