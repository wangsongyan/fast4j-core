/**
 * 
 */
package cn.wangsy.fast4j.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 时间操作
 * 
 * @author wangsy
 * @date 2016年12月7日下午12:24:44
 */
public class DateUtils {

	private DateUtils(){
		
	}
	
	/***
	 * 时间加减运算
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/***
	 * 天数加减运算
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		return add(date, Calendar.DATE, amount);
	}

	/***
	 * 月份加减运算
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	/***
	 * 年份加减运算
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	/**
	 * 根据出生日期计算年龄
	 * 
	 * @param standard
	 *            参照时间
	 * @param birth
	 *            出生日期
	 * @return
	 */
	public static int getAge(Date standard, Date birth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (birth != null) {
			now.setTime(standard);
			born.setTime(birth);
			if (born.after(now)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}

	/**
	 * 得到指定日期周一
	 * 
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		return calendar.getTime();
	}

	/**
	 * 得到指定日期周一
	 * 
	 * @return
	 */
	public static Date getMondayOfThisWeek() {
		return getMondayOfWeek(new Date());
	}

	/**
	 * 得到本周周日
	 * 
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return calendar.getTime();
	}

	/**
	 * 得到本周周日
	 * 
	 * @return
	 */
	public static Date getSundayOfThisWeek() {
		return getSundayOfWeek(new Date());
	}

}
