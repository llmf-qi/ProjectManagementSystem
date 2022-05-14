package Util;

public class Task {
	private String TaskName;
	private String TaskDueDate;
	private int TaskId;
	
	public void setId(int i) {
		TaskId = i;
	}
	
	public void setTaskName(String name) {
		TaskName = name;
	}
	
	public void setDueDate(String date) {
		TaskDueDate = date;
	}
	
	public int getId() {
		return TaskId;
	}
	
	public String getTaskName() {
		return TaskName;
	}
	
	public String getTaskDueDate() {
		return TaskDueDate;
	}
}
