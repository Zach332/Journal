package src.com.jou.main;
import java.util.*;
public class Reflection {
	private ArrayList<String> reflectionText;
	public Reflection() {

	}
	public void addReflection(String reflection) {
		reflectionText.add(reflection);
	}
	public void printReflection() {
		for(String paragraph: reflectionText) {
			System.out.println(paragraph);
			System.out.println();
		}
	}
}
