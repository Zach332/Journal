package src.com.jou.main;
import java.util.*;
import java.io.*;
public class Goals {
	private ArrayList<String> goals;
	public Goals() {
		goals = new ArrayList<String>();
	}
	public void addGoal(String goal) {
		this.goals.add(goal);
	}
	public String removeGoal(int index) {
		return goals.remove(index);
	}
	public void printGoals() {
		System.out.println("~Goals~");
		for(String goal: goals) {
			System.out.println(" - " + goal);
		}
		System.out.println();
	}
	public void writeData(BufferedWriter bw) {
		try {
			for(String goal: goals) {
				bw.write(goal);
				bw.newLine();
			}
			bw.write("END");
			bw.newLine();
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
}
