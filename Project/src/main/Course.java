package main;

import java.time.*;
import java.util.ArrayList;

public class Course {
    private String name;
    private int credits;
    private ArrayList<Course> prerequisites = new ArrayList<>();
    private ArrayList<Section> sections = new ArrayList<>();
    public static ArrayList<Course> allCourses = new ArrayList<>();

    public Course(String course, int credit, ArrayList<Course> prereqList, ArrayList<Section> sections) {
        this.name = course;
        this.credits = credit;
        this.prerequisites.addAll(prereqList);
        this.sections = sections;
    }

    public Course(String name, int credit, ArrayList<Course> prereqList) {
        this.name = name;
        this.credits = credit;
        this.prerequisites.addAll(prereqList);
    }

    public Course(Course course) {
        this.name = course.getName();
        this.credits = course.getCredits();
        this.prerequisites.addAll(course.getPrerequisites());
        this.sections = course.getSections();
    }
    public Course() {
        this.name = "Stats";
        this.credits = 4;
        this.prerequisites = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCredits(int c) {
        if (c > 0) {
            this.credits = c;
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

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void createSections(Department department) throws Exception {
        String departmentID = this.name.substring(0, 2); //we get ID so we can
        ArrayList<Course> term = Major.getTerm(department, this); //we want the term of the course so we can avoid conflicts
        ArrayList<LocalTime> times = new ArrayList<>(); // the times of the sections of the courses in the same term
        ArrayList<Course> removeList = new ArrayList<>(); //this is the list for the courses that are not the same department

        for (int i = 0; i <= term.size() - 1; i++) { //we add in the removeList
            Course course = term.get(i);
            String courseID = course.getName().substring(0, 2); //the substring of the name is the department code for the course name
            if (departmentID.equals(courseID)) {
                removeList.add(course);
            }
        }

        term.removeAll(removeList);

        for (int i = 0; i <= term.size() - 1; i++) {
            Course course = term.get(i);
            times.addAll(course.getCourseSectionsTime());
        }

        LocalTime sectionTime = Main.randomClassTime();
        for (int i = 0; i <= times.size() - 1; i++) {
            LocalTime time = times.get(i);
            if (time == sectionTime) {
                sectionTime = Main.randomClassTime();
            }
        }

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
                    ArrayList<Student> tempListForRegistration = (ArrayList<Student>) studentsNeedThisCourse.subList(20 * i, 20 * (i + 1));
                    tempStudentList.addAll(tempListForRegistration);
                } catch (IndexOutOfBoundsException e) {
                    ArrayList<Student> tempListForRegistration = (ArrayList<Student>) studentsNeedThisCourse.subList(20 * i, studentsNeedThisCourseSize);
                    tempStudentList.addAll(tempListForRegistration);
                }

                try {
                    Section section = new Section(this, prof, 20, sectionTime, Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                } catch (StudentRegistrationConflictException e) {
                    tempStudentList = e.removeStudents(tempStudentList);
                    Section section = new Section(this, prof, 20, sectionTime, Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                }
            }
        }
    }

    public void removeSections() {
        this.sections.clear();
    }

    public String getSectionsListAsString() {
        StringBuilder sectionListString = new StringBuilder();
        for(Section sect : sections) {
            sectionListString.append(sect.getID());
            sectionListString.append(", ");
        }
        return sectionListString.toString();
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

    public ArrayList<LocalTime> getCourseSectionsTime() {
        ArrayList<LocalTime> sectionsTime = new ArrayList<>();
        for (int i = 0; i <= this.sections.size() - 1; i++) {
            Section section = this.sections.get(i);
            LocalTime time = section.getSectionTime();
            sectionsTime.add(time);
        }
        return sectionsTime;
    }

    public String getCourseID() {
        int index = this.name.indexOf(1);
        for (int i = 2; i <= 4; i++) {
            int tempIndex = this.name.indexOf(i);
            if (tempIndex < index) {
                index = tempIndex; }
        }
        String courseID = this.name.substring(0, index);
        return courseID;
    }

    // toString
    @Override
    public String toString() {
        return " Course: " + this.name + " Credits: " + this.credits + " Sections: " + getSectionsListAsString();
    }
}