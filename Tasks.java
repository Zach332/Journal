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
		System.out.println("~Tasks~");
		for(int i = 0; i < tasks.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + tasks.get(i));
		}
		System.out.println();
	}
}
