package src.com.jou.main;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
public class DateFinder {
	private static SimpleDateFormat formatter;
	public DateFinder() {
		formatter = new SimpleDateFormat("EEEE: MM/dd/yyyy");
	}
	public static String getDate() {
		Date date = new Date();
		return formatter.format(date);
	}
}
