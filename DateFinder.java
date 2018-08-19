package src.com.jou.main;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;
public class DateFinder {
	private static SimpleDateFormat formatter;
	public DateFinder() {
		formatter = new SimpleDateFormat("EEEE: MM/dd/yyyy");
	}
	public static Date getDate() {
		return new Date();
	}
	public static String getDateString(Date date) {
		return formatter.format(date);
	}
	public static String getYear() {
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		return yearFormat.format(getDate());
	}
	public static String getDay(Date date) {
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
		return dayFormat.format(date);
	}
	public static Date subtractDay(Date date) {

		    Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return cal.getTime();
	}
	public static Date addDay(Date date) {

		    Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			return cal.getTime();
	}
}
