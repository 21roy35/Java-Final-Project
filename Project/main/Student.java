package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
        * Date: June 11-2022
        * This is the FullSectionsException class that extends Exception class
 * @author Team1
        *
        */


public class Student extends Person{
    /**
     * this is major name
     */
    Major major;
    /**
     * this is courses completed list
     */
    private ArrayList<Course> coursesCompleted = new ArrayList<>();
    /**
     * this is courses failed list
     */
    private ArrayList<Course> coursesFailed = new ArrayList<>();
    /**
     * credits completed
     */
    int creditsCompleted;
    /**
     * this is current sections list
     */
    private ArrayList<Section> currentSections = new ArrayList<>();
    /**
     * Students plan list
     */
    private ArrayList<ArrayList<Course>> studentPlan;
    /**
     * students failed list
     */
    public static ArrayList<Student> failed = new ArrayList<>();

    /**
     *
     * @param id
     * @param name
     * @param major
     */

	public Student(String id, String name, Major major) {
		super(id, name);
		this.major = major;
		this.studentPlan = major.createPlanForStudent();
	}

    /**
     *
     * @param method to set major
     */
	public void setMajor(Major major) {
		this.major = major;
	}

    /**
     *
     * @return  major
     */
	public Major getMajor() {
		return major;
	}

    /**
     *
     * @param method to add completed courses
     */
	public void addCoursesCompleted(Course... courses){
		Collections.addAll(coursesCompleted, courses);
	}

    /**
     *
     * @return Completed courses method
     */
	public ArrayList<Course> getCoursesCompleted(){
		return coursesCompleted;
	}

    /**
     *
     * @param add failed courses method
     */
	public void addCoursesFailed(Course... courses){
		Collections.addAll(coursesFailed, courses);
	}

    /**
     *
     * @return failed courses
     */
	public ArrayList<Course> getCoursesFailed(){
		return coursesFailed;
	}

    /**
     * method to set completed credits
     * @param creditsCompleted
     */
	public void setCreditsCompleted(int creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

    /**
     *
     * @return completed credits
     */
	public int getCreditsCompleted() {
		return creditsCompleted;
	}

    /**
     * method to add section
     * @param sections
     */
	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

    /**
     *
     * @return currents section
     */
	public ArrayList<Section> getCurrentSections(){
		return currentSections;
	}

    /**
     * method to remove current section
     * @param sections
     */
	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
	}

    /**
     * method to get students plan
     * @return student plan
     */
	public ArrayList<ArrayList<Course>> getStudentPlan() {
		return studentPlan;
	}

    /**
     * set the students plan
     * @param studentPlan
     */
	public void setStudentPlan(ArrayList<ArrayList<Course>> studentPlan) {
		this.studentPlan = studentPlan;
	}

    /**
     *  method to update student
     */
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

    /**
     * list of course needed
     * @return
     */
	public ArrayList<Course> neededCourses() {
		ArrayList<Course> neededCourses = new ArrayList<>();
		if (this.major.getCredits() != this.creditsCompleted) {
			int i = 0;
			for (ArrayList<Course> courseArray : this.studentPlan) {
				for (Course course : courseArray) {
					if (coursesCompleted.contains(course)) {
						break;
					} else {
						if (course.getPrerequisites().isEmpty()) {
							if (i != 5) {
								neededCourses.add(course);
								i++;
							}
						} else {
							for (Course preCourse : course.getPrerequisites()) {
								if (coursesCompleted.contains(preCourse)) {
									if (!neededCourses.contains(course)) {
										if (i != 5) {
											neededCourses.add(course);
											i++;
										}
									}
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
		return neededCourses;
	}

    /**
     * students section time list
     * @return section time
     */
	public ArrayList<LocalTime> getStudentSectionsTime() {
		ArrayList<LocalTime> sectionsTime = new ArrayList<>();
		for (int i = 0; i <= this.currentSections.size() - 1; i++) {
			Section section = this.currentSections.get(i);
			LocalTime time = section.getSectionTime();
			sectionsTime.add(time);
		}
		return sectionsTime;
	}

    /**
     *
     * @return major name
     */
	@Override
	public String toString() {
		return "" + name + " [major]: "+major;
	}

    /**
     *
     * @return department
     */
	public Department getDepartment() {
		for(Department department : main.Department.getAllDepartments()) {
			for(Student student : department.getStudentList()) {
				if(student.ID.equals(this.ID)) {
					return department;
				}
			}
	}
		
		return null;
	}
}

