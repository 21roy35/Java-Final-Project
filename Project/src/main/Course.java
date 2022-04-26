 package main;

import java.time.LocalTime;
import java.util.ArrayList;

public class Course {
		 private String name;
		 private int credits;
		 private ArrayList<Course> prerequisites=new ArrayList<Course>();
		 private ArrayList<Section> sections=new ArrayList<Section>();
		 private ArrayList<Professor> professors=new ArrayList<Professor>();

		 public Course(String course, int credit, ArrayList<Course> prereqList, ArrayList<Section> sections, ArrayList<Professor> professors) {
			 this.name=course;
			 this.credits=credit;
			 this.prerequisites=prereqList;
			 this.sections=sections;
			 this.professors=professors;
		 }
		 public Course() {
			 ArrayList<Course> prereq = new ArrayList<Course>();
			 this.name="Stats";
			 this.credits=4;
			 this.prerequisites=null;
			 this.sections=Section();
				for(int i =1; i<5; i++) {
					this.professors.add(new Professor());
				}
		 }
		 private ArrayList<Section> Section() {
			// TODO Auto-generated method stub
			return null;
		}
		public void setName(String name) {
		 	this.name=name;
		 }
		 public String getName() {
		 	return this.name;
		 }
		 public void setCredits(int c) {
		 	if (c>0) {
		 		this.credits=c;
		 }
		 }
		 public int getCredits() {
		 	return this.credits;
		 }
		 public void setPrerequisites(ArrayList<Course> c_list) {
		 	
		 }
		 public ArrayList<Course> getPrerequisites() {
		 	return this.prerequisites;
		 }
		 public void createSections(Department d) {
			 //	public Section(String name, Professor professor, int capacity, LocalTime time, ArrayList<Student> students, String sectionid)
			 //Section(d);
		 }
		 public void removeSections(Department d) {
		 	
		 }
		 public void setProfessors(ArrayList<Professor> professors) {
		 	
		 }
		 public ArrayList<Professor> getProfessors(){
			return this.professors;
		 	
		 }
		 public String getProfessorListAsString() {
			 String professorListString=new String();
			 for(Professor prof :professors) {
				 professorListString=professorListString + prof.name + ", ";
			 }
			 return professorListString;
		 }
		 public String getSectionsListAsString() {
			 String sectionListString=new String();
			 for(Section sect : sections) {
				 sectionListString=sectionListString + sect.getID() +", ";
			 }
			 return sectionListString;
		 }
		 // toString
		 @Override
		 public String toString() {
			 return "Course: " + this.name + "/nCredits: " + this.credits + "/nProfessors: " + getProfessorListAsString() + "Sections: " + getSectionsListAsString();
		 }
		 }