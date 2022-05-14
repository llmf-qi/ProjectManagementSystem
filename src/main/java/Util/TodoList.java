package Util;

import java.util.ArrayList;

public class TodoList {
	ArrayList<Task> TodoList = new ArrayList<Task>();
	public void addTask(Task t) {
		TodoList.add(t);
	}
	
	public ArrayList<Task> getTodoList(){
		return TodoList;
	}
	
	public void removeTask(int ID) {
		for(int i = 0; i < TodoList.size(); i++) {
			if(TodoList.get(i).getId()==ID) {
				TodoList.remove(i);
			}
		}
	}
}
