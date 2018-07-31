package src.com.jou.main;
import java.util.*;
public class Goals {
	private ArrayList<String> goals;
	public Goals() {
		goals = new ArrayList<String>();
	}
	public void addGoal(String goal) {
		this.goals.add(goal);
	}
	public void printGoals() {
		System.out.println("~Goals~");
		for(String goal: goals) {
			System.out.println(" - " + goal);
		}
		System.out.println();
	}
}
