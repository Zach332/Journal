package src.com.jou.main;
import java.io.*;
public class Init {
	public static void main(String[] args) {
		DateFinder dateFinder = new DateFinder();
		IO io = new IO();

		Data data = new Data("journalData.txt");
		if(Data.weeks.size() == 0) {
			Week week = new Week();
		}
		StateManager man = new StateManager(StateManager.State.DayView);
		man.appRun();

	}
}
