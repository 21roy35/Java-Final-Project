package Phase1;

import java.util.ArrayList;

public class Professor extends Person{
	
	ArrayList<Section> currentSections = new ArrayList<Section>();
	final int limit = 12;
	ArrayList<Course> courses = new ArrayList<Course>();
	
	public Professor(String id, String name,ArrayList<Course> courses) {
		super(id,name);
		this.courses = courses;
	}

	public Professor() {
		super("1001","John Doe");
		for(int i=1;5>i;i++) {
			//this.currentSections.add(new Section());
		}

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
