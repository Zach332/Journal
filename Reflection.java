package src.com.jou.main;
import java.util.*;
public class Reflection {
	private ArrayList<String> reflectionText;
	public Reflection() {
		reflectionText = new ArrayList<String>();
	}
	public void addReflection(String reflection) {
		reflectionText.add(reflection);
	}
	public void printReflection() {
		System.out.println("~Reflection~");
		for(String paragraph: reflectionText) {
			System.out.println(" " + paragraph);
			System.out.println();
		}
		System.out.println();
	}
}
