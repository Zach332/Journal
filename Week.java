package src.com.jou.main;
import java.util.*;
import java.io.*;
public class Week {
	private Tasks tasks;
	private Reflection reflection;
	private ArrayList<Day> days;
	private Goals goals;
	public Week() {
		days = new ArrayList<Day>();
		tasks = new Tasks();
		goals = new Goals();
		reflection = new Reflection();
	}
	public Week(Day day) {
		days = new ArrayList<Day>();
		tasks = new Tasks();
		goals = new Goals();
		reflection = new Reflection();
		Day tempDay = day;
		while(!(tempDay.getDay().equals("Monday"))) {
			tempDay = new Day(DateFinder.subtractDay(tempDay.getDate()));
			addDay(tempDay);
		}
		addDay(day);
		tempDay = day;
		while(!(tempDay.getDay().equals("Sunday"))) {
			tempDay = new Day(DateFinder.addDay(tempDay.getDate()));
			addDay(tempDay);
		}
		Data.addWeek(this);
		Data.sort();
	}
	public void addDay(Day day) {
		days.add(day);
	}
	public Day getDay(int index) {
		return days.get(index);
	}
	public Day getDay(Date date) {
		for(Day day: days) {
			if(DateFinder.getDateString(day.getDate()).equals(DateFinder.getDateString(date))) {
				return day;
			}
		}
		System.out.println("Date does not exist in this week.");
		return null;
	}
	public Day getLastDay() {
		return days.get(days.size() - 1);
	}
	public Date getLastDate() {
		return getLastDay().getDate();
	}
	public void addTask(String task) {
		tasks.addTask(task);
	}
	public void addReflection(String reflection) {
		this.reflection.addReflection(reflection);
	}	
	public void addGoal(String goal) {
		goals.addGoal(goal);
	}
	public void printWeek() {
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		System.out.println("--");
		System.out.println("Week of " + DateFinder.getDateString(getDay(0).getDate()) + " - " + DateFinder.getDateString(getLastDay().getDate()));
		for(int i = 0; i < days.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + DateFinder.getDateString(getDay(i).getDate()));
			System.out.println();
		}
		tasks.printTasks();
		goals.printGoals();
		reflection.printReflection();
	}
	public void printWeekSummary() {
		System.out.println("**Weekly information**");
		tasks.printTasks();
		goals.printGoals();

	}
	public void writeData(BufferedWriter bw) {
		try {
			bw.write("WEEK");
			bw.newLine();
			bw.write("WEEKTASKS");
			bw.newLine();
			tasks.writeData(bw);
			bw.write("WEEKGOALS");
			bw.newLine();
			goals.writeData(bw);
			bw.write("WEEKREFLECTION");
			bw.newLine();
			reflection.writeData(bw);
			for(Day day: days) {
				day.writeData(bw);
			}
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
	public boolean isInWeek(Date date) {
		if(date.compareTo(getLastDate()) <= 0) {
			if(date.compareTo(days.get(0).getDate()) >= 0) {
				return true;
			}
		}
		return false;
	}
}
