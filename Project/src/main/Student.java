package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Student extends Person{

	Major major;
	ArrayList<Course> coursesCompleted = new ArrayList<Course>();
	int creditsCompleted;
	ArrayList<Section> currentSections = new ArrayList<Section>();
	
	
	public Student(String id,String name,Major major) {
		super(id,name);
		this.major = major;
	}
	public Student() {
		super("100","Jane Doe");
		try {
			File file = new File("C:\\Users\\Mosmar\\git\\Java-Final-Project\\data\\Aerospace_ENG.txt");
			this.major = new Major(file);
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
}
