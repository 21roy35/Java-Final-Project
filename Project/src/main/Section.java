package main;

import java.time.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mansour Alasais
 * @since 5-25-22
 *
 */

public class Section {
	
	
	private Course course;
	private Professor professor;
	private int maxStudents;
	private LocalTime sectionTime;
	private String duration; // parsed as minutes 
	private ArrayList<Student> students = new ArrayList<Student>();
	private String sectionID;
	private static ArrayList<Section> sections = new ArrayList<Section>();
	
	
	public Section(Course course, Professor professor, int capacity, LocalTime time, String duration, Student students) {
//		for(Section section : getAllSections()) {
//			while(section.getStudentList().size()<section.maxStudents) {
//					section.addStudents(students);
//			}
//		}
		if(!sections.contains(this)) {
		this.course=course;
		this.duration=duration;
		this.professor=professor;
		this.maxStudents=capacity;
		this.sectionTime=time;
		this.students.add(students);
		students.addCurrentSections(this);
		this.sectionID = randomSectionID();
		sections.add(this);
		}
	}
	public Section(Course course, Professor professor, int capacity, LocalTime time, String duration, ArrayList<Student> students) {
//		for(Section section : getAllSections()) {
//			while(section.getStudentList().size() < section.maxStudents) {
//				for(Student student: students) {
//					section.addStudents(student);
//				}
//			}
//		}
		if(!sections.contains(this)) {
		this.course = course;
		this.duration = duration;
		this.professor = professor;
		this.maxStudents = capacity;
		this.sectionTime=time;
		this.students=students;
		for (int i = 0; i <= students.size() - 1; i++) {
			Student student = students.get(i);
			student.addCurrentSections(this);
		}
		this.sectionID=randomSectionID();
		sections.add(this);
		}
	}

	/**
	 * @param i as integer, sets the section ID to the given parameter
	 */
	public void setID(int i) {
		this.sectionID=String.valueOf(i);
	}
	/**
	 * @return sectionID as integer
	 */
	public String getID() {
		return this.sectionID;
	}
	/**
	 * @return sectionTime as LocalTime
	 */
	public LocalTime getSectionTime() {
		return this.sectionTime;
	}
	/**
	 * @param time as LocalTime, sets the given parameter to sectionTime
	 */
	public void setSectionTime(LocalTime time) {
		this.sectionTime=time;
	}
	/**
	 * @return ArrayList sections
	 */
	public static ArrayList<Section> getAllSections(){
		return sections;
	}
	/**
	 * @return maxStudents as integer
	 */
	public int getCapacity() {
		return maxStudents;
	}
	/**
	 * @param number as integer, sets maxStudents to the given parameter
	 */
	public void setCapacity(int number) {
		this.maxStudents=number;
	}
	/**
	 * @param prof as Professor, sets professor to the given parameter
	 */
	public void setProfessor(Professor prof) {
		this.professor=prof;
	}
	/**
	 * @return professor
	 */
	public Professor getProfessor() {
		return this.professor;
	}
	/**
	 * @param stu as Student, adds to the students ArrayList with the given parameter.
	 */
	public void addStudents(Student...stu) {
		for (Student stus : stu) {
			int len=students.size();
			for(int i=0; i<len; i++) {
				if (!students.get(i).equals(stus)) {
					this.students.add(stus);
				}
			}
		}
	}
	/**
	 * @param stu as Student, removes from the students ArrayList with the given parameter.
	 */
	public void removeStudents(Student...stu) {
		for (Student st : students) {
					for (Student s : stu) {
						if (st.equals(s)){
							students.remove(st);
						}
					}
		}
	}
	/**
	 * @return students
	 */
	public ArrayList<Student> getStudentList(){
		return this.students;
	}
		/**
		 * @return formatted text about the object.
		 */
		@Override
		public String toString() {
			return  "Course Name: " + course.getName() + "\nSection Id: " + sectionID +" \nTime: " + sectionTime + "\nNumber of students: " + students.size() + "\nProfessor: " + professor;
		}

	/**
	 * @return a random Section ID
	 */
	public static String randomSectionID() {
			return String.valueOf(new Random(7).nextInt()+10000);
	}

	public Course getCourse() {
		return this.course;
	}
	
}
