package main;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Date: June 12-2022
 * This is the Professor class extend person
 * @author Team1
 *
 */
public class Professor extends Person{
	ArrayList<Section> currentSections = new ArrayList<>();
	final int Limit = 12;
	ArrayList<Course> courses = new ArrayList<>();
	ArrayList<Professor> Professors = new ArrayList<>();

    /**
     * this a constructor that initiate an ID & name and a list of course to the professor
     * @param id assign an ID to the professor
     * @param name assign a name to the professor
     * @param courses initiate the course list
     */
	public Professor(String id, String name, ArrayList<Course> courses) {
		super(id, name);
		this.courses.addAll(courses);
		this.Professors.add(this);
	}

    /**
     * this is a method to add the current section
     * @param sections multiple sections
     */
	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
	}

    /**
     * this is  method to add course
     * @param course assing a course value
     */

	public void addCourse(Course course) {
		this.courses.add(course);
	}

    /**
     * this is a method to get the list of courses
     * @return courses
     */
	public ArrayList<Course> getCurrentCourses(){
		return courses;
	}

    /**
     * this is a method to get a professor limitation
     * @return profLimit
     */
	public int getLimit() {
		int profLimit = 0;
		for (Section section : this.currentSections) {
			int courseCredits = section.getCourse().getCredits();
			profLimit += courseCredits;
		}
		return profLimit;
	}

    /**
     * this is a method to show the professor name
     * @return
     */
	@Override
	public String toString() {
		return  this.name;
	}

    /**
     * this is a method to update the professor situation
     */
	public void updateProfessor() {
		this.currentSections.clear();
	}
}
