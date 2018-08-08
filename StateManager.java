//TODO
// Go to day- remember to switch curWeek
// new ongoing task
// complete/delete tasks
// every sunday, task is weekly reflection
// every monday, task is write weekly goals
// weekly reflection - have list of completed tasks that week
// carry incomplete tasks to next day
package src.com.jou.main;
import java.util.*;
public class StateManager {
	private State curState;
	public Day curDay;
	public Week curWeek;
	public StateManager(State startingState, Day curDay, Week curWeek) {
		curState = startingState;
		this.curDay = curDay;
		this.curWeek = curWeek;
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
		while(true) {
			try {
				String input = IO.readLine();
				if(input.equals("new task")) {
					curWeek.addTask(IO.readLine("Task: "));
					return;
				} else if(input.equals("new reflection")) {
					while(true) {
						String refInput = IO.readLine("What did you learn? (end to quit)");
						if(refInput.equals("end"))break;
						curWeek.addReflection(refInput);
					}
					return;
				} else if(input.equals("new goal")) {
					curWeek.addGoal(IO.readLine("Goal: "));
					return;
				} else if(input.equals("quit") || input.equals("exit")) {
					Data.save();
					System.exit(0);
				} else if(input.equals("help")) {
					System.out.println(" - new - (task, goal, reflection");
					System.out.println(" - day #");
					System.out.println(" - exit");
				} else if(input.equals("today")) {
					curDay = curWeek.getDay(DateFinder.getDate());
					curState = State.DayView;
					return;
				} else if(input.substring(0,3).equals("day")) {
					curDay = curWeek.getDay(Integer.parseInt(input.substring(4,5))-1);
					curState = State.DayView;
					return;
					//Below option is incomplete. Do not use
				} else if(input.substring(0,9).equals("go to day")) {
					curDay = curWeek.getDay(Integer.parseInt(input.substring(10,11))-1);
					if((curWeek = Data.getWeek(DateFinder.getDate())) != null) {
						curDay = curWeek.getDay(DateFinder.getDate());
					} else {
						curWeek = new Week(curDay = new Day());
					}
					curState = State.DayView;
					return;
				} else {
					System.out.println("Invalid input");
				}
			} catch(Exception E) {System.out.println("Invalid input");}
		}
	}
	public void dayView() {
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		curWeek.printWeekSummary();
		curDay.printDay();
		while(true) {
			String input = IO.readLine();
			if(input.equals("new task")) {
				curDay.addTask(IO.readLine("Task: "));
				return;
			} else if(input.equals("new note")) {
				curDay.addNote(IO.readLine("Note: "));
				return;
			} else if(input.equals("new reflection")) {
				while(true) {
					String refInput = IO.readLine("What did you learn? (end to quit)");
					if(refInput.equals("end"))break;
					curDay.addReflection(refInput);
				}
				return;
			} else if(input.equals("week")) {
				curState = State.WeekView;
				return;
			} else if(input.equals("quit") || input.equals("exit")) {
				Data.save();
				System.exit(0);
			} else if(input.equals("help")) {
				System.out.println(" - new - (task, note, reflection");
				System.out.println(" - week");
				System.out.println(" - exit");
			} else {
				System.out.println("Input invalid.");
			}
		}
	}
}
