package Util;

import java.util.ArrayList;

public class Project {
	private int id;
	private String Title;
	private String description;
	private TodoList todo;
	private ArrayList<String> members = new ArrayList<String>();
	
	public String getTitle() {
		return Title;
	}
	
	public TodoList getTasks(){
		return todo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setTitle(String t) {
		Title = t;
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public void setTasks(TodoList todo) {
		this.todo = todo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void addTask(Task t) {
		todo.addTask(t);
	}
	
	public ArrayList<String> getMemeber(){
		return members;
	}
	
	public void setMembers(ArrayList<String> m) {
		members = m;
	}
}
