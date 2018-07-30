package src.com.jou.main;
import java.io.*;
public class Init {
	public static void main(String[] args) {
		StateManager man = new StateManager(StateManager.State.DayView);

		System.out.println(man.getState());
	}
}
