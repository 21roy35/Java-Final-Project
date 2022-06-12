package main;

import java.time.*;
import java.util.ArrayList;
/**
 * Date: June 12-2022
 * This is the section class
 * @author Team1
 *
 */
public class Section {
    /**
     * this is course
     */
    private Course course;
    /**
     *this is professor
     */
    private Professor professor;
    /**
     * this is maximum students
     */
    private int maxStudents;
    /**
     * this is section time
     */
    private LocalTime sectionTime;
    /**
     * this is duration
     */
    private String duration; // parsed as minutes
    /**
     * this is a list of students
     */
    private ArrayList<Student> students = new ArrayList<Student>();
    /**
     * this is section ID
     */
    private String sectionID;
    /**
     * this is a list of sections
     */
    public static ArrayList<Section> sections = new ArrayList<Section>();

    /**
     * this a constructor that initiate course,professor,capacity,time,duration,student,Exception
     * @param course initiate course value
     * @param professor initiate professor value
     * @param capacity initiate capacity value
     * @param time initiate time value
     * @param duration  initiate duration value
     * @param student initiate student value
     * @throws Exception might throw  Exception
     */
	public Section(Course course, Professor professor, int capacity, LocalTime time, String duration, Student student) throws Exception{
		if(!sections.contains(this)) {
			this.course = course;
			this.duration = duration;
			this.professor = professor;
			this.maxStudents = capacity;
			this.sectionTime = time;

			if (student.getStudentSectionsTime().contains(time)) {
				throw new StudentRegistrationConflictException(student, this);
			}
			else {
				this.students.add(student);
				student.addCurrentSections(this);
			}

			this.sectionID = Main.randomSectionID();
			sections.add(this);
		}
	}

    /**
     * this is a constructor that initiate a course,professor,capacity,duration, list of students
     * @param course initiate course value
     * @param professor initiate professor value
     * @param capacity initiate capacity value
     * @param time initiate time value
     * @param duration initiate duration value
     * @param students initiate a list of students
     * @throws StudentRegistrationConflictException
     */
	public Section(Course course, Professor professor, int capacity, LocalTime time, String duration, ArrayList<Student> students) throws StudentRegistrationConflictException{
		if(!sections.contains(this)) {
			this.course = course;
			this.duration = duration;
			this.professor = professor;
			this.maxStudents = capacity;
			this.sectionTime = time;
			for (int i = 0; i <= students.size() - 1; i++) {
				Student student = students.get(i);

				if (student.getStudentSectionsTime().contains(time)) {
					throw new StudentRegistrationConflictException(student, this);
				}
				else {
					this.students.add(student);
					student.addCurrentSections(this);
				}
			}
			this.sectionID = Main.randomSectionID();
			sections.add(this);
		}
	}

	/**
     * this is a method to set an section ID
	 * @param i as integer, sets the section ID to the given parameter
	 */
	public void setID(int i) {
		this.sectionID=String.valueOf(i);
	}

	/**
     * this is method to get section ID
	 * @return sectionID as integer
	 */
	public String getID() {
		return this.sectionID;
	}

	/**
     * this is method to get section time
	 * @return sectionTime as LocalTime
	 */
	public LocalTime getSectionTime() {
		return this.sectionTime;
	}

	/**
     * this is method to get section time
	 * @param time as LocalTime, sets the given parameter to sectionTime
	 */
	public void setSectionTime(LocalTime time) {
		this.sectionTime=time;
	}

	/**
     * this is method to initiate a list of sections
	 * @return ArrayList sections
	 */
	public static ArrayList<Section> getAllSections(){
		return sections;
	}

	/**
     * this is a method to get the Capacity
	 * @return maxStudents as integer
	 */
	public int getCapacity() {
		return maxStudents;
	}

	/**
     * this is a method to set the capacity
	 * @param number as integer, sets maxStudents to the given parameter
	 */
	public void setCapacity(int number) {
		this.maxStudents=number;
	}

	/**
     * this is a method that sets the professor
	 * @param prof as Professor, sets professor to the given parameter
	 */
	public void setProfessor(Professor prof) {
		this.professor=prof;
	}

	/**
     * this is a method to get the professor
	 * @return professor
	 */
	public Professor getProfessor() {
		return this.professor;
	}

	/**
     * this is a method that add the students
	 * @param stu as Student, adds to the students ArrayList with the given parameter.
	 */
	public void addStudents(Student...stu) {
		for (Student student : stu) {
			int length = students.size();
			for(int i = 0; i < length; i++) {
				Student student1 = students.get(i);
				if (!student1.equals(student)) {
					this.students.add(student);
					student.addCurrentSections(this);
					break;
				}
			}
		}
	}

	/**
     * this is a method that remove the student
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
     * this is a method that return students list
	 * @return students
	 */
	public ArrayList<Student> getStudentList(){
		return this.students;
	}

	public void endTerm() {
		this.students.clear();
		this.professor = null;
	}

    /**
     * this is a method that check the empty sections
     */
	public static void checkEmptySections() {
		try {
			for (int i = 0; i <= sections.size() - 1; i++) {
				Section section = sections.get(i);
				if (section.getStudentList().size() == 0) {
					sections.remove(section);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			//ignore
		}
	}

	/**
     * this is a method to present 'course name , section ID, section time' number of students 'name of professor'.
	 * @return formatted text about the object.
	 */
	@Override
	public String toString() {
			return  "Course Name: " + course.getName() + "\nSection Id: " + sectionID +" \nTime: " + sectionTime + "\nNumber of students: " + students.size() + "\nProfessor: " + professor;
		}

	public Course getCourse() {
		return this.course;
	}
}
