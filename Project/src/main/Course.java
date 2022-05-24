package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {
    private String name;
    private int credits;
    private ArrayList<Course> prerequisites = new ArrayList<>();
    private ArrayList<Section> sections = new ArrayList<>();
    private ArrayList<Professor> professors=new ArrayList<>();
    public static ArrayList<Course> allCourses = new ArrayList<>();

    public Course(String course, int credit, ArrayList<Course> prereqList, ArrayList<Section> sections, ArrayList<Professor> professors) {
        this.name=course;
        this.credits=credit;
        this.prerequisites.addAll(prereqList);
        this.sections = sections;
        this.professors = professors;
    }

    //new Course(tempName, tempCredits, tempCourses)
    public Course(String name, int credit, ArrayList<Course> prereqList) {
        this.name = name;
        this.credits = credit;
        this.prerequisites.addAll(prereqList);
    }

    public Course(Course course) {
        this.name = course.getName();
        this.credits = course.getCredits();
        this.prerequisites.addAll(course.getPrerequisites());
    }
    public Course() {
        this.name="Stats";
        this.credits=4;
        this.prerequisites = null;
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
    public void setPrerequisites(ArrayList<Course> courseList) {
        this.prerequisites = courseList;
    }
    public ArrayList<Course> getPrerequisites() {
        return this.prerequisites;
    }
    public void createSections(Department department) {}
    //	public Section(Professor professor, LocalTime time)
    //Section(d);
    // Section creation depends on: Student.neededCourses(), Course.professors, Major.plan.get(term).sectionloop.(onlyDepartmentCourses),

    //Course course, Professor professor, int capacity, LocalTime time, LocalTime duration,ArrayList<Student> students, String sectionid)
    /*
     * for (Professor prof : this.professors) { // for each professor, a new section
     * will be created this.sections.add( this, prof, 20, LocalTime.now(), // time
     * should be defined in main method to avoid conflicts, LocalTime.of(00, 50), //
     * ignore hour field, mins will be used // students not defined here. // this
     * method should go to the main method (in the simulation)
     */
//		}

    public void removeSections(Department department) {

    }
    public void setProfessors(ArrayList<Professor> professors) {

    }
    public ArrayList<Professor> getProfessors(){
        return this.professors;

    }
    public String getProfessorListAsString() {
        String professorListString = new String();
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
        return "\n Course: " + this.name + "\n Credits: " + this.credits + "\n Professors: " + getProfessorListAsString() + "\n Sections: \n" + getSectionsListAsString();
    }
}