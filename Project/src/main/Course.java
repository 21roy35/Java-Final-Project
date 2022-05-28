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
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Professor> professorList = new ArrayList<>();

        for (int i = 0; i <= Department.allDepartments.size() - 1; i++) {
            ArrayList<Student> tempStudentList = Department.allDepartments.get(i).getStudentList();
            ArrayList<Professor> tempProfessorList = Department.allDepartments.get(i).getProfessorList();

            for (int r = 0; r <= tempStudentList.size() - 1; r++) {
                Student student = tempStudentList.get(r);
                studentList.add(student);
            }
            for (int r = 0; r <= tempProfessorList.size() - 1; r++) {
                Professor prof = tempProfessorList.get(r);
                professorList.add(prof);
            }
        }

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
            if (tempCourses.contains(this) & prof.getLimit() <= 12 - this.credits) {
                professorTeachThisCourse.add(prof);
            }
        }

        int studentsNeedThisCourseSize = studentsNeedThisCourse.size();
        int professorTeachThisCourseSize = professorTeachThisCourse.size();

        if (professorTeachThisCourse.size() == 0) {
            throw new NoAvailableProfessorException(this);
        } else {
            if ((studentsNeedThisCourseSize + professorTeachThisCourseSize - 1) /   professorTeachThisCourseSize > 20) {
                int index = professorTeachThisCourseSize*20;
                ArrayList<Student> studentsCouldNotRegister = (ArrayList<Student>) studentsNeedThisCourse.subList(index, studentsNeedThisCourseSize);
                throw new FullSectionsException(this, studentsCouldNotRegister);
            }

            for (int i = 0; i <= professorTeachThisCourseSize - 1; i++) {
                Professor prof = professorTeachThisCourse.get(i);
                ArrayList<Student> tempStudentList = new ArrayList<>();

                try {
                    tempStudentList.addAll((ArrayList<Student>) studentsNeedThisCourse.subList(20 * i, 20 * (i + 1)));
                } catch (IndexOutOfBoundsException e) {
                    tempStudentList.addAll((ArrayList<Student>) studentsNeedThisCourse.subList(20 * i, studentsNeedThisCourseSize));
                }

                try {
                    Section section = new Section(this, prof, 20, Main.randomClassTime(), Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                } catch (StudentRegistrationConflictException e) {
                    tempStudentList = e.removeStudents(tempStudentList);
                    Section section = new Section(this, prof, 20, Main.randomClassTime(), Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                }
            }
        }
    }

    public void removeSections() {
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