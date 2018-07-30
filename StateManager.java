package src.com.jou.main;
public class StateManager {
	private State curState;

	public StateManager(State startingState) {
		curState = startingState;
	}

	public enum State {
		DayView();
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
			}
		}
	}
	public void dayView() {
		curDay.printDay();
	}
}
