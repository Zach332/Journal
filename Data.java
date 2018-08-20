package src.com.jou.main;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.sql.Date;
public class Data {
	public static ArrayList<Week> weeks;
	private static File dataFile;
	private static String backupFileString = ".0";
	private static boolean carryTasks = false;
	private static java.util.Date lastLogin = null;
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
	public static int getWeekIndex(Week week) {
		for(int i = 0; i < weeks.size(); i++) {
			if(weeks.get(i).equals(week)) {
				return i;
			}
		}
		return -1;
	}
	public static void save() {
		try {
			File copyFile = new File("journalData.copy");
			Files.copy(dataFile.toPath(), copyFile.toPath());
			FileWriter writer = new FileWriter(dataFile);
			BufferedWriter bw = new BufferedWriter(writer);
			saveDate(bw);
			for(Week week: weeks) {
				week.writeData(bw);
			}
			bw.flush();
			bw.close();
			File backupFile = new File(backupFileString);
			Files.copy(dataFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			copyFile.delete();
		} catch (Exception E) {
			E.printStackTrace();
		}
		System.out.println("Save successful - data written to " + dataFile);
	}
	public static void saveDate(BufferedWriter bw) {
		try {
			bw.write("LASTLOGIN");
			bw.newLine();
			bw.write(String.valueOf(DateFinder.getDate().getTime()));
			bw.newLine();
			bw.write(backupFileString);
			bw.newLine();
		} catch(Exception E) {E.printStackTrace();}
	}
	public static void load() {
		java.util.Date today = DateFinder.getDate();
		File copyFile = new File("journalData.copy");
		if(copyFile.exists()) {
			System.out.println("There was an unexpected error during the last save session. Please transfer the data from journalData.copy to journalData.txt, then delete the journalData.copy file.");
			System.exit(0);
		}
		if(!(dataFile.exists())) {
			initializeFiles();
		}
		try {
			FileReader reader = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			Week curWeek = null;
			Day curDay = null;
			while((line = br.readLine()) != null) {
				if(line.equals("LASTLOGIN")) {
					line = br.readLine();
					lastLogin = new java.util.Date(new java.sql.Date(Long.valueOf(line)).getTime());
					if(!(DateFinder.getDateString(today).equals(DateFinder.getDateString(lastLogin)))) {
						carryTasks = true;
					}
					line = br.readLine();
					if(line.equals(".4")) {
						backupFileString = ".0";
					} else {
						int temp = Integer.parseInt(line.substring(1))+1;
						backupFileString = "." + String.valueOf(temp);
					}
				}
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
				if(line.equals("COMPLETEDTASKS")) {
					while(!((line = br.readLine()).equals("END"))) {
						curWeek.addCompletedTask(line);
					}
				}
				if(line.equals("DAY")) {
					line = br.readLine();
					java.util.Date tempDate = new java.util.Date(new java.sql.Date(Long.valueOf(line)).getTime());
					curWeek.addDay(new Day(tempDate, true));
					curDay = Data.getCurDay();
				}
				if(line.equals("TASKS")) {
					while(!((line = br.readLine()).equals("END"))) {
						curDay.addTask(line);
					}
					while(!((line = br.readLine()).equals("END"))) {
						curDay.addDailyTask(line);
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
	public static void carryTasks() {
		if(carryTasks == true) {
			ArrayList<ArrayList<String>> taskStrings = new ArrayList<ArrayList<String>>();
			Week tempWeek = getWeek(lastLogin);
			int weekIndex = getWeekIndex(tempWeek);
			Day tempDay = tempWeek.getDay(lastLogin);
			while(!(DateFinder.getDateString(tempDay.getDate()).equals(DateFinder.getDateString(DateFinder.getDate())))) {
				taskStrings.add(tempDay.getTasksStrings());
				java.util.Date tempDate = DateFinder.addDay(tempDay.getDate());
				if((tempDay = tempWeek.getDay(tempDate)) == null) {
					weekIndex++;
					tempWeek = weeks.get(weekIndex);
					tempDay = tempWeek.getFirstDay();
				} 
			}
			for(ArrayList<String> a: taskStrings) {
				for(String task: a) {
					tempDay.addTask(task);
				}
			}
		}
	}
	public static void initializeFiles() {
		try {
			dataFile.createNewFile();
			File f0= new File(".0");
			f0.createNewFile();
			File f1 = new File(".1");
			f1.createNewFile();
			File f2 = new File(".2");
			f2.createNewFile();
			File f3 = new File(".3");
			f3.createNewFile();
			File f4 = new File(".4");
			f4.createNewFile();
		} catch(Exception E) {
			E.printStackTrace();
			System.out.println("Error initializing data files.");
			System.exit(0);
		}
		System.out.println("Welcome to your journal!");
		System.out.println();
		System.out.println("This journal is designed to help you stay organized and focused on your primary goals. Below are some tips to help you make the most of this journal.");
		System.out.println();
		IO.readLine("Press enter to continue.");
		System.out.println();
		System.out.println(" - Write your goals for the week every Monday. These should be general statements of what you want to accomplish that you can turn into actionable tasks on individual days.");
		System.out.println();
		System.out.println(" - Write a reflection at the end of every day about what you learned that day; this will help you remember the most important parts of your days and enable you to use those lessons in the future.");
		System.out.println();
		System.out.println(" - Write a weekly reflection every Sunday. This should be about your success in completing your weekly goals and daily tasks, and how you can improve in future weeks.");
		System.out.println();
		System.out.println(" - Write daily tasks every morning to help you focus on what you want to accomplish. At the end of the day, complete the tasks you finished and delete any you decide are unimportant. Any remaining tasks will, by default, carry over to the next day.");
		System.out.println();
		IO.readLine("Press enter to continue.");
		System.out.println();
		System.out.println("Remember to always type \"exit\" at the end of each session to save your data. Content is not saved automatically.");
		System.out.println();
		IO.readLine("Press enter to continue.");
		System.out.println();
		System.out.println("These are the basic functions of the journal. I hope it benefits you and helps you stay focused throughout your days. You can always type \"help\" in the program to see possible commands and their syntax. You can also visit this project's Github page (journal, by Zach332) to learn more and see advanced tips. Enjoy!");
		System.out.println();
		IO.readLine("Press enter to continue to the journal.");
	}
	public static void addOngoingTask(java.util.Date startDate, java.util.Date endDate, String task) {
		java.util.Date curDate = startDate;
		while(curDate.compareTo(endDate) <= 0) {
			Week curWeek;
			Day curDay;
			if((curWeek = getWeek(curDate)) == null) {
				curWeek = new Week(new Day(curDate));
			}
			curDay = curWeek.getDay(curDate);
			System.out.println(DateFinder.getDateString(curDay.getDate()));
			curDay.addDailyTask(task);
			curDate = DateFinder.addDay(curDate);
		}
	}
	public static void sort() {
		ArrayList<Week> tempArr = new ArrayList<Week>();
		while(weeks.size() > 0) {
			java.util.Date min = weeks.get(0).getLastDate();
			int minIndex = 0;
			for(int i = 1; i < weeks.size(); i++) {
				if(weeks.get(0).getLastDate().compareTo(min) < 0) {
					min = weeks.get(0).getLastDate();
					minIndex = i;
				}
			}
			tempArr.add(weeks.remove(minIndex));
		}
		weeks = tempArr;
	}
	public static Week getWeek(java.util.Date date) {
		// binary search
		int baseline = 0;
		int highline = weeks.size() - 1;
		while(baseline <= highline) {
			int middle = (highline + baseline) / 2;
			Week middleWeek = weeks.get(middle);
			if(middleWeek.isInWeek(date)) {
				return middleWeek;
			}
			if(date.compareTo(middleWeek.getLastDate()) >= 0) {
				baseline = middle + 1;
			}
			if(date.compareTo(middleWeek.getLastDate()) <= 0) {
				highline = middle - 1;
			}	
		}
		return null;
	}
	public static java.util.Date stringToDate(String dateString) {
		if(dateString.length() <= 5) {
			dateString = DateFinder.getYear() + "-" + dateString;
		}
		try {
			java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
			java.util.Date date = new Date(sqlDate.getTime());
			return date;
		} catch(Exception E) {
			return null;
		}
	}
}
