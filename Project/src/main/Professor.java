package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Professor extends Person{
	ArrayList<Section> currentSections = new ArrayList<>();
	final int limit = 12;
	final int profLimit = 92;
	ArrayList<Course> courses = new ArrayList<>();
	ArrayList<Professor> Professors = new ArrayList<>();
	
	public Professor(String id, String name, ArrayList<Course> courses) {
		super(id, name);
		if(this.Professors.size()<profLimit) {
		for(Professor prof : this.Professors) {
			if(!prof.currentSections.containsAll(currentSections)) {
				this.Professors.add(this);
				this.courses.addAll(courses);
			}
		}
		}
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
		for (Course course : this.courses) {
			profLimit += course.getCredits();
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
