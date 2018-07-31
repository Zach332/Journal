package src.com.jou.main;
import java.util.*;
public class Tasks {
	private ArrayList<String> tasks;
	public Tasks() {
		tasks = new ArrayList<String>();
	}
	public void addTask(String task) {
		this.tasks.add(task);
	}
	public void printTasks() {
		for(String task: tasks) {
			System.out.println("- " + task);
		}
	}
}
