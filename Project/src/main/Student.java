package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Student extends Person{
	Major major;
	private ArrayList<Course> coursesCompleted = new ArrayList<>();
	private ArrayList<Course> coursesFailed = new ArrayList<>();
	int creditsCompleted;
	private ArrayList<Section> currentSections = new ArrayList<>();
	private ArrayList<ArrayList<Course>> studentPlan;
	public static ArrayList<Student> failed = new ArrayList<>();


	public Student(String id, String name, Major major) {
		super(id, name);
		this.major = major;
		this.studentPlan = major.createPlanForStudent();
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Major getMajor() {
		return major;
	}

	public void addCoursesCompleted(Course... courses){
		Collections.addAll(coursesCompleted, courses);
	}

	public ArrayList<Course> getCoursesCompleted(){
		return coursesCompleted;
	}

	public void addCoursesFailed(Course... courses){
		Collections.addAll(coursesFailed, courses);
	}

	public ArrayList<Course> getCoursesFailed(){
		return coursesFailed;
	}

	public void setCreditsCompleted(int creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

	public int getCreditsCompleted() {
		return creditsCompleted;
	}

	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

	public ArrayList<Section> getCurrentSections(){
		return currentSections;
	}

	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
	}

	public void updateStudent() {
		Random rand = new Random();
		int failFactor = rand.nextInt(100);
		
		if (failFactor >= 5){
			int credit = 0;
			for(Section section: this.currentSections) {
				Course course = section.getCourse();
				credit += course.getCredits();
				this.coursesCompleted.add(course);
			}

			this.creditsCompleted += credit;
			this.currentSections.clear();
		}
		else {
			if(!Student.failed.contains(this)) {
				Student.failed.add(this);
			}
			try {
				int failCourse = rand.nextInt(this.currentSections.size());

				Section failedSection = this.currentSections.get(failCourse);
				coursesFailed.add(failedSection.getCourse());
				this.currentSections.remove(failCourse);
				failedSection.getStudentList().remove(this);
			} catch (IllegalArgumentException e) {
				//ignore
			}

			int credit = 0;
			for(Section section: this.currentSections) {
				Course course = section.getCourse();
				credit += course.getCredits();
				this.coursesCompleted.add(course);
			}

			this.creditsCompleted += credit;
			this.currentSections.clear();
		}
	}

	public ArrayList<Course> neededCourses() {
		ArrayList<Course> neededCourses = new ArrayList<>();
		int i = 0;
		for(ArrayList<Course> courseArray: this.studentPlan){
			for(Course course: courseArray) {
				if(coursesCompleted.contains(course)) {
					break;
				}
				else {
					if(course.getPrerequisites().isEmpty()) {
						if(i!=6) {
							neededCourses.add(course);
							i++;
						}
					}
					else {
						for(Course preCourse: course.getPrerequisites()) {
							if(coursesCompleted.contains(preCourse)) {
								if(!neededCourses.contains(course)) {
									if(i!=6) {
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

