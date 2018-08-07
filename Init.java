package src.com.jou.main;
import java.io.*;
public class Init {
	public static void main(String[] args) {
		DateFinder dateFinder = new DateFinder();
		IO io = new IO();

		Data data = new Data("journalData.txt");
		Data.load();
		if(Data.weeks.size() == 0) {
			Week week = new Week();
			data.addWeek(week);
			week.addDay(new Day());
		}
		Data.getCurWeek().printWeek();
		if(!(Data.getCurDay().getDate().equals(DateFinder.getDate()))){
			Data.getCurWeek().addDay(new Day());
		}
		StateManager man = new StateManager(StateManager.State.DayView);
		man.appRun();

	}
}
