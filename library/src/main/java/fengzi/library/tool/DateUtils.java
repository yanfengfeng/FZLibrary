package fengzi.library.tool;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 2015-6-7
 * @author yff 这是时间类的一些工具
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

	/** 时间日期格式化到年月日时分秒. */
	public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	/** 时间日期格式化到年月日. */
	public static final String FORMAT_YMD = "yyyy-MM-dd";

	/** 时间日期格式化到年月. */
	public static final String FORMAT_YM = "yyyy-MM";

	/** 时间日期格式化到年月日时分. */
	public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

	/** 时间日期格式化到月日. */
	public static final String FORMAT_MD = "MM/dd";

	/** 时分秒. */
	public static final String FORMAT_HMS = "HH:mm:ss";

	/** 时分. */
	public static final String FORMAT_HM = "HH:mm";

	/** 上午. */
	public static final String AM = "AM";

	/** 下午. */
	public static final String PM = "PM";

	/**
	 * 获取当前时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCuurTime() {

		return getCuurTime(FORMAT_YMDHMS);
	}
	/**
	 * 获取当前时间
	 * @return 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getCuurTime(String  format) {
		
		return timeMillis2Date(System.currentTimeMillis(),format);
	}

	/**
	 * 时间戳转化为时间
	 * 
	 * @param time
	 *            : 时间戳
	 * @return yyyy-MM-dd HH:mm:ss
	 */

	public static String timeMillis2Date(long time) {

		return timeMillis2Date(time, FORMAT_YMDHMS);
	}

	/**
	 * 时间戳转化为时间
	 * 
	 * @param time
	 *            : 时间戳
	 * @param timeFormt
	 *            : 转化后的格式
	 * @return timeFormt
	 */

	public static String timeMillis2Date(long time, String timeFormt) {
		if(TextUtils.isEmpty(timeFormt)){
			timeFormt = FORMAT_YMDHMS;
		}
		Date date = new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormt);
		return simpleDateFormat.format(date);
	}

	/**
	 * 时间转化为时间戳
	 * 
	 * @param time  yyyy-MM-dd
	 *            : 时间
	 * @return long
	 */

	public static long date2TimeMillis(String time) {

		return date2TimeMillis(time, FORMAT_YMD);
	}

	/**
	 * 时间转化为时间戳
	 * 
	 * @param time
	 *            : 时间
	 * @param timeFormt
	 *            : 时间格式
	 * @return long
	 */

	public static long date2TimeMillis(String time, String timeFormt) {
		if (TextUtils.isEmpty(time)) {
			return 0;
		}

		if (TextUtils.isEmpty(timeFormt)) {
			timeFormt = FORMAT_YMDHMS;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormt);
		Date date;
		try {
			date = simpleDateFormat.parse(time);
//			LogUtil.d("=====date2TimeMillis======" + date.getTime() / 1000);
			return date.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取当日
	 * 
	 * @return
	 */
	public static int getCurrDay() {

		Calendar c = Calendar.getInstance();

		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前yue 
	 * 
	 * @return
	 */
	public static int getCurrMonth() {

		Calendar c = Calendar.getInstance();

		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static String getCurrYear() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 获得当前时间的毫秒数
	 * 
	 * @return
	 */
	public static long getCuurentTime() {

		return System.currentTimeMillis();
	}

	/**
	 * 生日转化为年龄(以年为单位进行计算)
	 * 
	 * @return
	 */
	public static String birthday2age(String birthday) {

		if (TextUtils.isEmpty(birthday) || birthday.length() < 4) {
			return "1970";
		}
		int bir = Integer.parseInt(birthday.substring(0, 4));
		int cur_year = Integer.parseInt(getCurrYear());
		return (cur_year - bir) + "";
	}


	/**
	 * 描述：计算两个日期所差的天数.
	 * 
	 * @param milliseconds1
	 *            the milliseconds1
	 * @param milliseconds2
	 *            the milliseconds2
	 * @return int 所差的天数
	 */
	public static int getOffectDay(long milliseconds1, long milliseconds2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(milliseconds1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(milliseconds2);
		// 先判断是否同年
		int y1 = calendar1.get(Calendar.YEAR);
		int y2 = calendar2.get(Calendar.YEAR);
		int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
		int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
		int maxDays = 0;
		int day = 0;
		if (y1 - y2 > 0) {
			maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 + maxDays;
		} else if (y1 - y2 < 0) {
			maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 - maxDays;
		} else {
			day = d1 - d2;
		}
		return day;
	}

	/**
	 * 描述：计算两个日期所差的小时数.
	 * 
	 * @param date1
	 *            第一个时间的毫秒表示
	 * @param date2
	 *            第二个时间的毫秒表示
	 * @return int 所差的小时数
	 */
	public static int getOffectHour(long date1, long date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
		int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
		int h = 0;
		int day = getOffectDay(date1, date2);
		h = h1 - h2 + day * 24;
		return h;
	}

	/**
	 * 描述：计算两个日期所差的分钟数.
	 * 
	 * @param date1
	 *            第一个时间的毫秒表示
	 * @param date2
	 *            第二个时间的毫秒表示
	 * @return int 所差的分钟数
	 */
	public static int getOffectMinutes(long date1, long date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int m1 = calendar1.get(Calendar.MINUTE);
		int m2 = calendar2.get(Calendar.MINUTE);
		int h = getOffectHour(date1, date2);
		int m = 0;
		m = m1 - m2 + h * 60;
		return m;
	}

	/**
	 * 取指定日期为星期几.
	 * 
	 * @param strDate
	 *            指定日期
	 * @param inFormat
	 *            指定日期格式
	 * @return String 星期几
	 */
	public static String getWeekNumber(String strDate, String inFormat) {
		String week = "星期日";
		Calendar calendar = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat(inFormat);
		try {
			calendar.setTime(df.parse(strDate));
		} catch (Exception e) {
			return "错误";
		}
		int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (intTemp) {
		case 0:
			week = "星期日";
			break;
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		}
		return week;
	}

	/**
	 * 根据给定的日期判断是否为上下午.
	 * 
	 * @param strDate
	 *            the str date
	 * @param format
	 *            the format
	 * @return the time quantum
	 */
	public static String getTimeQuantum(String strDate, String format) {
		Date mDate = getDateByFormat(strDate, format);
		int hour = mDate.getHours();
		if (hour >= 12)
			return "PM";
		else
			return "AM";
	}

	/**
	 * 描述：String类型的日期时间转化为Date类型.
	 * 
	 * @param strDate
	 *            String形式的日期时间
	 * @param format
	 *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return Date Date类型日期时间
	 */
	public static Date getDateByFormat(String strDate, String format) {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = mSimpleDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
