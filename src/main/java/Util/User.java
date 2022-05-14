package Util;

import java.util.ArrayList;

public class User {
 private String UserName;
 private String UserEmail;
 private String Password;
 private int UserId;
 private ArrayList<String> projects = new ArrayList<String>();
 private ArrayList<Integer> projIDs = new ArrayList<Integer>();
 
 public User(String UserName, String UserEmail, String Password) {
  this.UserName = UserName;
  this.UserEmail = UserEmail;
  this.Password = Password;
 }
 
 public User(int id) {
  this.UserId = id;
 }
 
 public void setUserName(String UserName) {
  this.UserName = UserName;
 }
 
 public void setUserEmail(String UserEmail) {
  this.UserEmail = UserEmail;
 }
 
 public void setPassword (String Password) {
  this.Password = Password;
 }
 
 public void setId(int id) {
  this.UserId = id;
 }
 
 public void setProjects(ArrayList<String> p){
   this.projects = p;
  
 }
 public void setProjId(ArrayList<Integer> pID){
   this.projIDs = pID;
 }
 
 public int getId() {
  return this.UserId;
 }
 
 public String getUsername() {
  return this.UserName;
 }
 
 public String getUseremail() {
  return this.UserEmail;
 }
 
 public String getUserpassword() {
  return this.Password;
 }
 
 public ArrayList<String> getProjects(){
  return projects;
  
 }
 
 public ArrayList<Integer> getProjId(){
  return projIDs;
  
 }
 
 public void addProject(String title) {
  projects.add(title);
 }
}