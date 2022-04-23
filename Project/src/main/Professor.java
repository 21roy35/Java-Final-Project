package main;

import java.util.ArrayList;

public class Professor extends Person{
	
	ArrayList<Section> currentSections = new ArrayList<Section>();
	final int limit = 12;
	
	public Professor(String id, String name,ArrayList<Section> currentSections) {
		super(id,name);
		this.currentSections = currentSections;
	}
	public Professor(String id, String name) {
		super(id,name);
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
	public ArrayList<Section> getCurrentCourses(){
		return currentSections;
	}
	public int getLimit() {
		return limit;
	}

}
