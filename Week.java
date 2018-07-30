package src.com.jou.main;
import java.util.*;
public class Week {
	private ArrayList<Day> days;
	public Week() {
		days = new ArrayList<Day>();
	}
	public void addDay(Day day) {
		days.add(day);
	}
	public Day getDay(int index) {
		return days.get(index);
	}
	public Day getLastDay() {
		return days.get(days.size() - 1);
	}
}
