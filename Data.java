package src.com.jou.main;
import java.util.*;
public class Data {
	public ArrayList<Week> weeks;
	public Week getCurWeek() {
		return weeks.get(weeks.size() - 1);
	}
	public Day getCurDay() {
		return getCurWeek().getLastDay();
	}
	public Data() {
		weeks = new ArrayList<Week>();
	}
	public void addWeek(Week week) {
		weeks.add(week);
	}
}
