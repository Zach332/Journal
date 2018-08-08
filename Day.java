package src.com.jou.main;
import java.io.*;
import java.util.*;
public class Day {
	private Tasks tasks;
	private Notes notes;
	private Reflection reflection;
	private Date date;
	public Day() {
		date = DateFinder.getDate();
		tasks = new Tasks();
		notes = new Notes();
		reflection = new Reflection();
	}
	public Day(Date date) {
		this.date = date;
		tasks = new Tasks();
		notes = new Notes();
		reflection = new Reflection();
	}
	public Date getDate() {
		return date;
	}
	public String getDay() {
		return DateFinder.getDay(date);
	}
	public void addTask(String task) {
		tasks.addTask(task);
	}
	public void addNote(String note) {
		notes.addNote(note);
	}
	public void addReflection(String reflection) {
		this.reflection.addReflection(reflection);
	}	
	public void printDay() {
		
		System.out.println("---------------------");
		System.out.println("**" + DateFinder.getDateString(date) + "**");
		tasks.printTasks();
		notes.printNotes();
		reflection.printReflection();
	}
	public void writeData(BufferedWriter bw) {
		try {
			bw.write("DAY");
			bw.newLine();
			bw.write(date.toString());
			bw.newLine();
			bw.write("TASKS");
			bw.newLine();
			tasks.writeData(bw);
			bw.write("NOTES");
			bw.newLine();
			notes.writeData(bw);
			bw.write("REFLECTION");
			bw.newLine();
			reflection.writeData(bw);
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
	
}
