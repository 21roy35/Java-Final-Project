package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Person{

	Major major;
	private ArrayList<Course> coursesCompleted = new ArrayList<Course>();
	int creditsCompleted;
	private ArrayList<Section> currentSections = new ArrayList<Section>();
	private ArrayList<ArrayList<Course>> studentPlan;
	
	public Student(String id, String name, Major major) {
		super(id,name);
		this.major = major;
		this.studentPlan = this.major.createPlanForStudent();
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
		
		for(Section section: this.currentSections) {
			Course course = section.getCourse();
			this.coursesCompleted.add(course);
		}
		int credit = 0;
		for(Course c: this.coursesCompleted) {
			credit += c.getCredits();
		}
		this.creditsCompleted += credit;
		this.currentSections.clear();
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

	public int getCreditsCompleted() {
		return creditsCompleted;
	}

	public void setCreditsCompleted(int creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

	public ArrayList<LocalTime> getStudentSectionsTime() {
		ArrayList<LocalTime> sectionsTime = new ArrayList<>();
		for (int i = 0; i <= this.currentSections.size() - 1; i++) {
			Section section = this.currentSections.get(i);
			LocalTime time = section.getSectionTime();
			sectionsTime.add(time);
		}
		return sectionsTime;
	}
	
	@Override
	public String toString() {
		return "" + name + " [major]: "+major;
	}
}

