package src.com.jou.main;
import java.io.*;
import java.util.*;
public class Init {
	public static void main(String[] args) {
		DateFinder dateFinder = new DateFinder();
		IO io = new IO();
		Data data = new Data("journalData.txt");
		Data.load();
		Day curDay = null;
		Week curWeek = null;
		if(Data.weeks.size() == 0) {
			curWeek = new Week(curDay = new Day());
			data.addWeek(curWeek);
		} else if((curWeek = Data.getWeek(DateFinder.getDate())) != null) {
			curDay = curWeek.getDay(DateFinder.getDate());
		} else {
			curWeek = new Week(curDay = new Day());
			data.addWeek(curWeek);
		}
		Data.sort();
		StateManager man = new StateManager(StateManager.State.DayView, curDay, curWeek);
		man.appRun();

	}
}
