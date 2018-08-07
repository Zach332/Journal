package src.com.jou.main;
import java.util.*;
import java.io.*;
public class Notes {
	private ArrayList<String> notes;
	public Notes() {
		notes = new ArrayList<String>();
	}
	public void addNote(String note) {
		this.notes.add(note);
	}
	public void printNotes() {
		System.out.println("~Notes~");
		for(String note: notes) {
			System.out.println(" - " + note);
		}
		System.out.println();
	}
	public void writeData(BufferedWriter bw) {
		try {
			for(String note: notes) {
				bw.write(note);
				bw.newLine();
			}
			bw.write("END");
			bw.newLine();
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
}
