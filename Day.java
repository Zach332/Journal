package src.com.jou.main;
public class Day {
	private Tasks tasks;
	private Notes notes;
	private Reflection reflection;
	private String date;
	public Day() {
		date = DateFinder.getDate();
		tasks = new Tasks();
		notes = new Notes();
		reflection = new Reflection();
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
		System.out.println("**" + date + "**");
		tasks.printTasks();
		notes.printNotes();
		reflection.printReflection();
	}
	
}
