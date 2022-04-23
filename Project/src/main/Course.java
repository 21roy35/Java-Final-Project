 package main;

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
		 	//
		 }
		 public void removeSections(Department d) {
		 	
		 }
		 public void setProfessors(ArrayList<Professor> professors) {
		 	
		 }
		 public ArrayList<Professor> getProfessors(){
			return this.professors;
		 	
		 }
		 }