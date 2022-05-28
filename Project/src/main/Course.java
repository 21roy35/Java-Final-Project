package main;

import java.lang.reflect.Array;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {
    private String name;
    private int credits;
    private ArrayList<Course> prerequisites = new ArrayList<>();
    private ArrayList<Section> sections = new ArrayList<>();
    public static ArrayList<Course> allCourses = new ArrayList<>();

    public Course(String course, int credit, ArrayList<Course> prereqList, ArrayList<Section> sections) {
        this.name=course;
        this.credits=credit;
        this.prerequisites.addAll(prereqList);
        this.sections = sections;
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
//        for(int i =1; i<5; i++) {
//            this.professors.add(new Professor());
//        }
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

    public void createSections(Department department) throws Exception {
    //public Section(Professor professor, LocalTime time)
    //Section(d);
    //Section creation depends on: Student.neededCourses(), Course.professors, Major.plan.get(term).sectionloop.(onlyDepartmentCourses),
        ArrayList<Student> studentList = department.getStudentList();
        ArrayList<Professor> professorList = department.getProfessorList();
        ArrayList<Student> studentsNeedThisCourse = new ArrayList<>();
        ArrayList<Professor> professorTeachThisCourse = new ArrayList<>();

        for (int i = 0; i <= studentList.size() - 1; i++) {
            Student student = studentList.get(i);
            ArrayList<Course> tempCourses = student.neededCourses();
            if (tempCourses.contains(this)) {
                studentsNeedThisCourse.add(student);
            }
        }

        for (int i = 0; i <= professorList.size() - 1; i++) {
            Professor prof = professorList.get(i);
            ArrayList<Course> tempCourses = prof.getCurrentCourses();
            if (tempCourses.contains(this)) {
                professorTeachThisCourse.add(prof);
            }
        }

        if (professorTeachThisCourse.size() == 0) {//throw exception and create section!!!
            throw new Exception("NoAvailableProfessorException. No professors teach the course: " + this.name);
        }
        else if ((studentsNeedThisCourse.size() + professorTeachThisCourse.size() - 1)/professorTeachThisCourse.size() > 20) {
            throw new Exception("NotEnoughProfessorsException. need more professors to teach the course: " + this.name);
        }
        else {
            for (int i = 0; i <= professorTeachThisCourse.size() - 1; i++) {
                ArrayList<Student> tempStudentList = (ArrayList<Student>) studentsNeedThisCourse.subList(0, 20);

                Section section = new Section(this, professorTeachThisCourse.get(i), 20, Main.randomClassTime(), Main.randomClassDuration(), tempStudentList);
                this.sections.add(section);
                professorTeachThisCourse.get(i).addCurrentSections(section);
            }
        }



    //Course course, Professor professor, int capacity, LocalTime time, LocalTime duration,ArrayList<Student> students, String sectionid)
    /*
     * for (Professor prof : this.professors) { // for each professor, a new section
     * will be created this.sections.add( this, prof, 20, LocalTime.now(), // time
     * should be defined in main method to avoid conflicts, LocalTime.of(00, 50), //
     * ignore hour field, mins will be used // students not defined here. // this
     * method should go to the main method (in the simulation)
     */
    }

    //	 public static void createSections() throws Exception {
//		 for(Student student : current_students) {
//			 ArrayList<Course> neededCourses = student.neededCourses();
//			 for(Course course: neededCourses) {
//				 int credits = course.getCredits();
//				 Section section =  new Section(course, newProfessor(1).get(0), 20, randomClassTime(), randomClassDuration(), student);
//			 }
//		 }
//	 }

    public void removeSections(Department department) {
        this.sections.clear();
    }

//    public void setProfessors(ArrayList<Professor> professors) {
//        this.professors = professors;
//    }
//
//    public ArrayList<Professor> getProfessors(){
//        return this.professors;
//    }
//
//    public String getProfessorListAsString() {
//        String professorListString = new String();
//        for(Professor prof :professors) {
//            professorListString=professorListString + prof.name + ", ";
//        }
//        return professorListString;
//    }

    public String getSectionsListAsString() {
        String sectionListString = new String();
        for(Section sect : sections) {
            sectionListString = sectionListString + sect.getID() +", ";
        }
        return sectionListString;
    }

    public static Course searchForCourse(String name) {
        Course wantedCourse = null;
        for (int s = 0; s <= Course.allCourses.size() - 1; s++) {
            Course tempCourse = Course.allCourses.get(s);
            if (tempCourse.getName().equals(name))
                wantedCourse = tempCourse;
        }
        return wantedCourse;
    }

    // toString
    @Override
    public String toString() {
        return " Course: " + this.name + " Credits: " + this.credits + " Sections: " + getSectionsListAsString();
    }
}