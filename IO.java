package src.com.jou.main;
import java.util.*;
public class IO {
	private static Scanner scanner;
	public IO() {
		scanner = new Scanner(System.in);
	}
	public static String readLine() {
		return scanner.nextLine();
	}
	public static String readLine(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}
}
