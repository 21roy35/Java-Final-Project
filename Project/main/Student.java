package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Date: June 11-2022
 * This is the student class that extends person class
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
     *this is constructor that initil ID & name and major for the student
     * @param id initiate id for student
     * @param name initiate name for the student
     * @param major initiate major for the student
     */

	public Student(String id, String name, Major major) {
		super(id, name);
		this.major = major;
		this.studentPlan = major.createPlanForStudent();
	}

    /**
     *this is method to set the major
     * @param major assign major value
     */
	public void setMajor(Major major) {
		this.major = major;
	}

    /**
     *this is method to get the major
     * @return major major value
     */
	public Major getMajor() {
		return major;
	}

    /**
     *this is a method to add completed courses
     * @param courses add completed courses
     */
	public void addCoursesCompleted(Course... courses){
		Collections.addAll(coursesCompleted, courses);
	}

    /**
     * method to get a list of completed courses
     * @return completed course
     */
	public ArrayList<Course> getCoursesCompleted(){
		return coursesCompleted;
	}

    /**
     * method to add failed courses
     * @param  courses failed courses
     */
	public void addCoursesFailed(Course... courses){
		Collections.addAll(coursesFailed, courses);
	}

    /**
     *method to get a list of failed courses
     * @return failed courses
     */
	public ArrayList<Course> getCoursesFailed(){
		return coursesFailed;
	}

    /**
     * method to set completed credits
     * @param creditsCompleted credits completed value
     */
	public void setCreditsCompleted(int creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

    /**
     *this is method to get completed credits
     * @return completed credits
     */
	public int getCreditsCompleted() {
		return creditsCompleted;
	}

    /**
     * method to add section
     * @param sections add multiple sections
     */
	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

    /**
     * this is a list to get current sections
     * @return currents section
     */
	public ArrayList<Section> getCurrentSections(){
		return currentSections;
	}

    /**
     * method to remove current section
     * @param sections remove current sections
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
     * method to set the student plan
     * @param studentPlan initiate student plan
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
     * @return needed courses
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
     * this is a list to get student sections time
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
     * this is method to present name & major
     * @return name & major
     */
	@Override
	public String toString() {
		return "" + name + " [major]: "+major;
	}

    /**
     * this is method to get department
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

