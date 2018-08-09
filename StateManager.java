//TODO
// new ongoing task
// delete tasks (and other things if needed)
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
				if(input.equals("new task") || input.equals("nt")) {
					curWeek.addTask(IO.readLine("Task: "));
					return;
				} else if(input.equals("new reflection") || input.equals("nr")) {
					while(true) {
						String refInput = IO.readLine("What did you learn? (end to quit)");
						if(refInput.equals("end"))break;
						curWeek.addReflection(refInput);
					}
					return;
				} else if(input.equals("new goal") || input.equals("ng")) {
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
					curWeek = Data.getWeek(DateFinder.getDate());
					curDay = curWeek.getDay(DateFinder.getDate());
					curState = State.DayView;
					return;
				} else if(input.equals("go to day") || input.equals("gtd")) {
					System.out.println("Enter the day in this format - yyyy-[m]m-[d]d");
					String tempInput = IO.readLine();
					java.util.Date tempDate = Data.stringToDate(tempInput);
					if(tempDate == null) {
						System.out.println("Date is invalid.");
						return;
					}
					if((curWeek = Data.getWeek(tempDate)) != null) {
						curDay = curWeek.getDay(tempDate);
					} else {
						curWeek = new Week(curDay = new Day(tempDate));
					}
					curState = State.DayView;
					return;
				} else if(input.substring(0,3).equals("day")) {
					curDay = curWeek.getDay(Integer.parseInt(input.substring(4,5))-1);
					curState = State.DayView;
					return;
				} else if(input.substring(0,8).equals("complete")) {
					curWeek.addCompletedTask(curWeek.removeTask(Integer.parseInt(input.substring(9,input.length())) - 1));
					return;
				} else {
					System.out.println("Input invalid.");
				}
			} catch(Exception E) {E.printStackTrace();System.out.println("Your input is invalid. Please try again or type \"help\" for help.");}
		}
	}
	public void dayView() {
		for(int i = 0; i < 20; i++) {
			System.out.println();
		}
		curWeek.printWeekSummary();
		curDay.printDay();
		while(true) {
			try {
				String input = IO.readLine();
				if(input.equals("new task") || input.equals("nt")) {
					curDay.addTask(IO.readLine("Task: "));
					return;
				} else if(input.equals("new note") || input.equals("nn")) {
					curDay.addNote(IO.readLine("Note: "));
					return;
				} else if(input.equals("new reflection") || input.equals("nr")) {
					while(true) {
						String refInput = IO.readLine("What did you learn? (end to quit)");
						if(refInput.equals("end"))break;
						curDay.addReflection(refInput);
					}
					return;
				} else if(input.equals("week") || input.equals("w")) {
					curState = State.WeekView;
					return;
				} else if(input.equals("quit") || input.equals("exit")) {
					Data.save();
					System.exit(0);
				} else if(input.equals("help")) {
					System.out.println(" - new - (task, note, reflection");
					System.out.println(" - week");
					System.out.println(" - exit");
				} else if(input.substring(0,8).equals("complete")) {
					curWeek.addCompletedTask(curDay.removeTask(Integer.parseInt(input.substring(9,input.length())) - 1));
					return;
				} else {
					System.out.println("Input invalid.");
				}
			} catch(Exception E) {E.printStackTrace();System.out.println("Your input is invalid. Please try again or type \"help\" for help.");}
		}
	}
}
