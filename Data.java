package src.com.jou.main;
import java.nio.file.*;
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
			File copyFile = new File("journalData.copy");
			Files.copy(dataFile.toPath(), copyFile.toPath());
			FileWriter writer = new FileWriter(dataFile);
			BufferedWriter bw = new BufferedWriter(writer);
			for(Week week: weeks) {
				week.writeData(bw);
			}
			bw.flush();
			bw.close();
			copyFile.delete();
		} catch (Exception E) {
			E.printStackTrace();
		}
		System.out.println("Save successful - data written to " + dataFile);
	}
	public static void load() {
		File copyFile = new File("journalData.copy");
		if(copyFile.exists()) {
			System.out.println("There was an unexpected error during the last save session. Please transfer the data from journalData.copy to journalData.txt, then delete the journalData.copy file.");
			System.exit(0);
		}
		try {
			FileReader reader = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			Week curWeek = null;
			Day curDay = null;
			while((line = br.readLine()) != null) {
				if(line.equals("WEEK")) {
					curWeek = new Week();
					weeks.add(curWeek);
				}
				if(line.equals("WEEKTASKS")) {
					while(!((line = br.readLine()).equals("END"))) {
						curWeek.addTask(line);
					}
				}
				if(line.equals("WEEKGOALS")) {
					while(!((line = br.readLine()).equals("END"))) {
						curWeek.addGoal(line);
					}
				}
				if(line.equals("WEEKREFLECTION")) {
					while(!((line = br.readLine()).equals("END"))) {
						curWeek.addReflection(line);
					}
				}
				if(line.equals("DAY")) {
					line = br.readLine();
					curWeek.addDay(new Day(line));
					curDay = Data.getCurDay();
				}
				if(line.equals("TASKS")) {
					while(!((line = br.readLine()).equals("END"))) {
						curDay.addTask(line);
					}
				}
				if(line.equals("NOTES")) {
					while(!((line = br.readLine()).equals("END"))) {
						curDay.addNote(line);
					}
				}
				if(line.equals("REFLECTION")) {
					while(!((line = br.readLine()).equals("END"))) {
						curDay.addReflection(line);
					}
				}
			}
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
}
