package main;

import java.util.ArrayList;
import java.util.Collections;

public class Professor extends Person{
	ArrayList<Section> currentSections = new ArrayList<>();
	final int limit = 12;
	ArrayList<Course> courses;
	
	public Professor(String id, String name,ArrayList<Course> courses) {
		super(id, name);
		this.courses = courses;
	}

	public void addCurrentSections(Section... sections){
		Collections.addAll(currentSections, sections);
	}

	public void removeCurrentSections(Section... sections){
		for(Section section : sections) {
			currentSections.remove(section);
		}
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
}
