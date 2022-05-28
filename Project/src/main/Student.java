package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Person{

	Major major;
	ArrayList<Course> coursesCompleted = new ArrayList<Course>();
	int creditsCompleted;
	ArrayList<Section> currentSections = new ArrayList<Section>();
	ArrayList<Course> failedCourses = new ArrayList<Course>();
	String status = "Normal";
	
	
	public Student(String id,String name,Major major) {
		super(id,name);
		this.major = major;
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
	public void updateStudent() {
		Random rand = new Random();
		int failFactor = rand.nextInt(100);
		//System.out.println(failFactor + "FFFFFF");
		if(failFactor>80) {
			int numOfCourses = rand.nextInt(3);
				this.status = "Not on plan";
				for(Course course: this.coursesCompleted) {
					for(int i= 0; i<numOfCourses;i++) {
						this.failedCourses.add(this.coursesCompleted.get(i));
					}
				}
		}
		else {
		
		for(Section section: this.currentSections) {
			this.coursesCompleted.add(section.getCourse());
		}
		int credit = 0;
		for(Course c: this.coursesCompleted) {
			credit += c.getCredits();
		}
		this.creditsCompleted = credit;
		this.currentSections.clear();
		}
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
	public ArrayList<Course> neededCourses() {
		ArrayList<Course> neededCourses = new ArrayList<Course>();
		int i = 0;
			for(ArrayList<Course> courseArray: major.getPlan()){
				for(Course course: courseArray) {
					if(coursesCompleted.contains(course)) {
						break;
						}
					else {
						if(course.getPrerequisites().isEmpty()) {
							if(i!=5) {
								neededCourses.add(course);
								i++;
								}
							}
						else {
							for(Course preCourse: course.getPrerequisites()) {
								if(coursesCompleted.contains(preCourse)) {
									if(!neededCourses.contains(course)) {
										if(i!=5) {
											neededCourses.add(course);
											i++;
											}
										}
									}
								else {
									break;
								}
							}
						}
					}
				}
			}
		return neededCourses;
	}
	
	@Override
	public String toString() {
		return "" + name + " [major]: "+major;
	}
}

