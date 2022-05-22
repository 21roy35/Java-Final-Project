package main;

import java.time.*;
import java.util.ArrayList;
import java.util.Random;

public class Section {
	private Course course;
	private Professor professor;
	private int maxStudents;
	private LocalTime sectionTime;
	private String duration; // parsed as minutes 
	private ArrayList<Student> students = new ArrayList<Student>();
	private String sectionID;
	private static ArrayList<Section> sections = new ArrayList<Section>();
	
	
	public Section(Course course, Professor professor, int capacity, LocalTime time, String duration,ArrayList<Student> students, String sectionid) {
		this.course=course;
		this.duration=duration;
		this.professor=professor;
		this.maxStudents=capacity;
		this.sectionTime=time;
		this.students=students;
		this.sectionID=sectionid;
		sections.add(this);
	}
	public Section(Course course, Professor professor,LocalTime time) {
		for(int i = 0;i<new Random(2).nextInt();i++) {
			this.students.add(new Student());
		}
		this.duration="50";
		this.maxStudents=new Random(7).nextInt()+1;
		this.sectionID=String.valueOf(new Random(7).nextInt()+10000);
		this.course=course;
		this.professor=professor;
		this.sectionTime=time;
	}
	
//
//	public Section() { // ----------------------------------- a method for testing
//		int rand = new Random().nextInt(1000);
//		this.course=new Course();
//		this.professor=new Professor();;
//		this.maxStudents=new Random().nextInt(20);
//		this.sectionTime=LocalTime.parse(randomClassTime());
//		for(int i =1; i<this.maxStudents; i++) {
//			this.students.add(new Student());
//		}
//		this.sectionID=String.valueOf(rand);
//	} // -------------------------------------------------------------------------------
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
public static ArrayList<Section> getAllSections(){
	return sections;
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
		return  "Course Name: " + course.getName() + "\nSection Id: " + sectionID +" \nTime: " + sectionTime + "\nNumber of students: " + students.size() + "\nProfessor: " + professor;
	}
	
}
