package src.com.jou.main;
import java.io.*;
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
	public String removeTask(int index) {
		return tasks.remove(index);
	}
	public void writeData(BufferedWriter bw) {
		try {
			for(String task: tasks) {
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
