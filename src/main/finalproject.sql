DROP DATABASE IF EXISTS finalproject;
   CREATE DATABASE finalproject;
   USE finalproject;
CREATE TABLE Projects (
  groupID INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NULL,
  description VARCHAR(45) NULL,
  PRIMARY KEY (groupID)
  );
  
  CREATE TABLE users_has_groups (
  users_userID INT NOT NULL,
  groups_groupID INT NOT NULL);

CREATE TABLE users (
  userID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  password VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  PRIMARY KEY (userID));
  
  CREATE TABLE tasks (
  taskID INT NOT NULL,
  tasks_groupID INT NOT NULL,
  taskInfo VARCHAR(45) NULL,
  taskDueDate VARCHAR(45) NULL,
  PRIMARY KEY (taskID));
  
ALTER TABLE tasks
;
ALTER TABLE tasks
ADD CONSTRAINT groupID
  FOREIGN KEY (tasks_groupID)
  REFERENCES projects (groupID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE users_has_groups 
;
ALTER TABLE users_has_groups
ADD CONSTRAINT userID
  FOREIGN KEY (users_userID)
  REFERENCES users (userID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE projects
ADD COLUMN groupID_joining VARCHAR(45) NULL AFTER description;

ALTER TABLE users_has_groups
ADD CONSTRAINT groupID_joining
  FOREIGN KEY (groups_groupID)
  REFERENCES projects (groupID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE projects
DROP COLUMN groupID_joining;
  
