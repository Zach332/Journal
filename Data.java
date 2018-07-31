package src.com.jou.main;
import java.util.*;
public class Data {
	public static ArrayList<Week> weeks;
	public static Week getCurWeek() {
		return weeks.get(weeks.size() - 1);
	}
	public static Day getCurDay() {
		return getCurWeek().getLastDay();
	}
	public Data() {
		weeks = new ArrayList<Week>();
	}
	public static void addWeek(Week week) {
		weeks.add(week);
	}
}
