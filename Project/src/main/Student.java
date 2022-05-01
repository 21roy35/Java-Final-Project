package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Student extends Person{

	Major major;
	ArrayList<Course> coursesCompleted = new ArrayList<Course>();
	int creditsCompleted;
	ArrayList<Section> currentSections = new ArrayList<Section>();
	public Student(String id,String name,Major major, ArrayList<Course> coursesCompleted) {
		super(id,name);
		this.major = major;
		this.coursesCompleted = coursesCompleted;
	}
	public Student() {
		super("100","Jane Doe");
		for(int i =1; i<5; i++) {
			this.coursesCompleted.add(new Course());
		}
		try {
			this.major = new Major(null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public void addCoursesCompleted(Course... courses){
		for(Course course : courses) {
			coursesCompleted.add(course);
		}
	}
	public ArrayList<Course> getCoursesCompleted(){
		return coursesCompleted;
	}
	public void addCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.add(section);
		}
	}
	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
	}
	public ArrayList<Section> getCurrentSections(){
		return currentSections;
	}
}
