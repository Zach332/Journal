package src.com.jou.main;
import java.util.*;
public class Notes {
	private ArrayList<String> notes;
	public Notes() {

	}
	public void addNote(String note) {
		this.notes.add(note);
	}
	public void printNotes() {
		for(String note: notes) {
			System.out.println("- " + note);
		}
	}
}
