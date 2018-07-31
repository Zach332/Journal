package src.com.jou.main;
import java.util.*;
public class StateManager {
	private State curState;
	private Day curDay;
	private Week curWeek;
	public StateManager(State startingState) {
		curState = startingState;
		curDay = Data.getCurDay();
		curWeek = Data.getCurWeek();
	}
	public enum State {
		DayView(),
		WeekView();
	}
	public void changeState(State state) {
		curState = state;
	}
	public State getState() {
		return curState;
	}
	public void appRun() {
		while(true) {
			if(curState == StateManager.State.DayView) {
				dayView();
			} else if(curState == State.WeekView) {
				weekView();
			}
		}
	}
	public void weekView() {
		curWeek.printWeek();
		String input = IO.readLine();
		try {
			if(input.equals("new task")) {
				curWeek.addTask(IO.readLine("Task: "));
			} else if(input.equals("new reflection")) {
				while(true) {
					String refInput = IO.readLine("What did you learn? ");
					if(refInput.equals("end"))break;
					curWeek.addReflection(refInput);
				}
			} else if(input.equals("new goal")) {
				curWeek.addGoal(IO.readLine("Goal: "));
			} else if(input.substring(0,3).equals("day")) {
				curDay = curWeek.getDay(Integer.parseInt(input.substring(4,5))-1);
				curState = State.DayView;
				return;
			} else {
				System.out.println("Invalid input");
			}
		} catch(Exception E) {System.out.println("Invalid input");}
	}
	public void dayView() {
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		curWeek.printWeekSummary();
		curDay.printDay();
		String input = IO.readLine();
		if(input.equals("new task")) {
			curDay.addTask(IO.readLine("Task: "));
		} else if(input.equals("new note")) {
			curDay.addNote(IO.readLine("Note: "));
		} else if(input.equals("new reflection")) {
			while(true) {
				String refInput = IO.readLine("What did you learn? ");
				if(refInput.equals("end"))break;
				curDay.addReflection(refInput);
			}
		} else if(input.equals("week")) {
			curState = State.WeekView;
			return;
		} else {
			System.out.println("Input invalid.");
		}
	}
}
