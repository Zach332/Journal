package src.com.jou.main;
import java.io.*;
import java.util.*;
public class Tasks {
	private ArrayList<String> tasks;
	private ArrayList<String> carryTasks;
	private ArrayList<String> dailyTasks;
	public Tasks() {
		tasks = new ArrayList<String>();
		dailyTasks = new ArrayList<String>();
		carryTasks = new ArrayList<String>();
	}
	public ArrayList<String> getTaskStrings() {
		return carryTasks;
	}
	public void addTask(String task) {
		this.tasks.add(task);
		this.carryTasks.add(task);
	}
	public void addDailyTask(String task) {
		this.tasks.add(task);
		this.dailyTasks.add(task);
	}
	public void printTasks() {
		System.out.println("~Tasks~");
		for(int i = 0; i < tasks.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + tasks.get(i));
		}
		System.out.println();
	}
	public String removeTask(int index) {
		String temp = tasks.remove(index);
		carryTasks.remove(temp);
		return temp;
	}
	public void writeData(BufferedWriter bw) {
		try {
			for(String task: carryTasks) {
				bw.write(task);
				bw.newLine();
			}
			bw.write("END");
			bw.newLine();
			for(String task: dailyTasks) {
				bw.write(task);
				bw.newLine();
			}
			bw.write("END");
			bw.newLine();
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
}
