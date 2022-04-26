package main;

import java.time.*;
import java.util.ArrayList;
import java.util.Random;

public class Section {
	private String CourseName;
	private Professor professor;
	private int maxStudents;
	private LocalTime sectionTime;
	private ArrayList<Student> students = new ArrayList<Student>();
	private String sectionID;
	public Section(String name, Professor professor, int capacity, LocalTime time, ArrayList<Student> students, String sectionid) {
		this.CourseName=name;
		this.professor=professor;
		this.maxStudents=capacity;
		this.sectionTime=time;
		this.students=students;
		this.sectionID=sectionid;
	}
	public Section() { // ----------------------------------- a method for testing
		int rand = new Random().nextInt(1000);
		this.CourseName="Course 1";
		this.professor=new Professor();;
		this.maxStudents=(int) Math.random()+1;
		this.sectionTime=LocalTime.parse("12:00");
		for(int i =1; i<5; i++) {
			this.students.add(new Student());
		}
		this.sectionID=String.valueOf(rand);
	} // -------------------------------------------------------------------------------
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
				for (Student s : stu) {
					if (st.equals(s)){
						students.remove(st);
					}
				}
	}
}
public ArrayList<Student> getStudentList(){
	return this.students;
}
	@Override
	public String toString() {
		return  "Course Name: " + CourseName + "\nSection Id: " + sectionID +" \nTime: " + sectionTime + "\nNumber of students: " + students.size() + "\nProfessor: " + professor;
	}
	
}
