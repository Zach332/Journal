package src.com.jou.main;
import java.text.SimpleDateFormat;
import java.util.*;
public class Date {
	private static SimpleDateFormat formatter;
	public Date() {
		formatter = new SimpleDateFormat("EEEE: MM/dd/yyyy");
	}
	public static String getDate() {
		Date date = new Date();
		return formatter.format(date);
	}
}
