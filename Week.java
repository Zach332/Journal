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
		Data.addWeek(this);
		addDay(new Day());
		tasks = new Tasks();
		goals = new Goals();
		reflection = new Reflection();
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
		System.out.println("Week of " + getDay(0).getDate() + " - " + getLastDay().getDate()) ;
		for(int i = 0; i < days.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + getDay(i).getDate());
			System.out.println();
		}
		tasks.printTasks();
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
}
