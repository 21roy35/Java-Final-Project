package main;

import java.time.*;
import java.util.ArrayList;

public class Section {
	private String CourseName;
	private Professor professor;
	private int maxStudents;
	private LocalTime sectionTime;
	private ArrayList<Student> students = new ArrayList<Student>();
	private String sectionID;
	public Section(String name, String professor, int capacity, LocalTime time, ArrayList<Student> students, String sectionid) {
		this.CourseName=name;
		this.professor=professor;
		this.maxStudents=capacity;
		this.sectionTime=time;
		this.students=students;
		this.sectionID=sectionid;
	}
public void setID(int i) {
	this.sectionID=String.valueOf(i);
}
public String getID() {
	return this.sectionID;
}
public LocalTime getSectionTime() {
	return this.sectionTime;
}
public void setSectionTime(LocalTime time) {
	this.sectionTime=time;
}
public int getCapacity() {
	return maxStudents;
}
public void setCapacity(int a) {
	this.maxStudents=a;
}
public void setProfessor(Professor prof) {
	this.professor=prof;
}
public Professor getProfessor() {
	return this.professor;
}
public void addStudents(Student...stu) {
	for (Student stus : stu) {
		int len=students.size();
		for(int i=0; i<len; i++) {
		    if (!students.get(i).equals(stus)) {
				this.students.add(stus);
		    }
		}
	}
}
public void removeStudents(Student...stu) {
	for (Student st : students) {
				
	}
}
	@Override
	public String toString() {
		return  CourseName + " Time: " + sectionTime + "Number of students: " + students.size() + "Professor: " + professor;
	}
	
}
