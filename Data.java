package src.com.jou.main;
import java.util.*;
import java.io.*;
public class Data {
	public static ArrayList<Week> weeks;
	private static File dataFile;
	public static Week getCurWeek() {
		return weeks.get(weeks.size() - 1);
	}
	public static Day getCurDay() {
		return getCurWeek().getLastDay();
	}
	public Data(String dataFile) {
		weeks = new ArrayList<Week>();
		this.dataFile = new File(dataFile);
	}
	public static void addWeek(Week week) {
		weeks.add(week);
	}
	public static void save() {
		try {
			FileWriter writer = new FileWriter(dataFile);
			BufferedWriter bw = new BufferedWriter(writer);
			for(Week week: weeks) {
				week.writeData(bw);
			}
			bw.flush();
			bw.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
		System.out.println("Save successful - data written to " + dataFile);
	}
}
