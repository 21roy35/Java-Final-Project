package main;

import java.util.ArrayList;
import java.util.Collections;

public class Professor extends Person{
	ArrayList<Section> currentSections = new ArrayList<>();
	final int Limit = 12;
	ArrayList<Course> courses = new ArrayList<>();
	ArrayList<Professor> Professors = new ArrayList<>();
	
	public Professor(String id, String name, ArrayList<Course> courses) {
		super(id, name);
		this.courses.addAll(courses);
		this.Professors.add(this);
	}

	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public ArrayList<Course> getCurrentCourses(){
		return courses;
	}

	public int getLimit() {
		int profLimit = 0;
		for (Section section : this.currentSections) {
			int courseCredits = section.getCourse().getCredits();
			profLimit += courseCredits;
		}
		return profLimit;
	}

	@Override
	public String toString() {
		return  this.name;
	}

	public void updateProfessor() {
		this.currentSections.clear();
	}
}
