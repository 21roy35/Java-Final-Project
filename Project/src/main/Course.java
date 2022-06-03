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
        this.sections.addAll(sections);
    }

    public Course(String name, int credit, ArrayList<Course> prereqList) {
        this.name = name;
        this.credits = credit;
        this.prerequisites.addAll(prereqList);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCredits(int credits) {
        if (credits > 0) {
            this.credits = credits;
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

    public void createSections() throws Exception {
        ArrayList<Course> term = Major.getTerm(this); //we want the term of the course, so we can avoid conflicts
        ArrayList<LocalTime> times = new ArrayList<>(); // the times of the sections of the courses in the same term

        for (int i = 0; i <= term.size() - 1; i++) { //in this loop, we collect the sections time to avoid them
            Course course = term.get(i); //get course
            ArrayList<LocalTime> sectionTime = course.getCourseSectionsTime(); //get the course sections time
            times.addAll(sectionTime); //add the sections time to the list
        }

        LocalTime sectionTime = Main.randomClassTime(); //create a random section time
        for (int i = 0; i <= times.size() - 1; i++) { //check whether the section time is appropriate by comparing with times list
            LocalTime time = times.get(i);
            if (time.equals(sectionTime)) {
                sectionTime = Main.randomClassTime();
            }
        }

        ArrayList<Student> studentsNeedThisCourse = new ArrayList<>();
        ArrayList<Professor> professorTeachThisCourse = new ArrayList<>();

        for (int i = 0; i <= Department.allDepartments.size() - 1; i++) { //in this loop, we get allDepartments students and professors related to this course
            ArrayList<Student> tempStudentList = Department.allDepartments.get(i).getStudentList();
            ArrayList<Professor> tempProfessorList = Department.allDepartments.get(i).getProfessorList();

            for (int r = 0; r <= tempStudentList.size() - 1; r++) { //first we check the student who need this course
                Student student = tempStudentList.get(r); //we get the student
                ArrayList<Course> tempCourses = student.neededCourses(); //we get the student needed courses
                if (tempCourses.contains(this)) { //we look if his needed courses contain this course
                    studentsNeedThisCourse.add(student);
                }
            }

            for (int r = 0; r <= tempProfessorList.size() - 1; r++) { //second we check the professor if he teaches the course
                Professor prof = tempProfessorList.get(r); //we get the professor
                ArrayList<Course> tempCourses = prof.getCurrentCourses(); //we get the current courses he teaches
                boolean profLimit = prof.getLimit() <= 12 - this.credits;
                if (tempCourses.contains(this) & profLimit) { //we check if he teaches this course, and he did not exceed his limit
                    professorTeachThisCourse.add(prof);
                }
            }
        }

        //this two integers is for the sizes of the two lists (students, professors)
        int studentsListSize = studentsNeedThisCourse.size();
        int professorsListSize = professorTeachThisCourse.size();

        if (studentsListSize == 0) { //we check if there is any students who need this course
            //skip the creation of sections because no student reached the course yet
        }
        else if (professorTeachThisCourse.size() == 0) { //we check if there is any available professors
            throw new NoAvailableProfessorException(this);
        }
        else { //else create sections for this class
            if ((studentsListSize + professorsListSize - 1) /   professorsListSize > 20) { //first, we check if the professors are not enough
                //if they are not enough, we will throw FullSectionsException and add the student who could not register there
                int index = professorsListSize * 20;
                ArrayList<Student> studentsCouldNotRegister = new ArrayList<>(studentsNeedThisCourse.subList(index, studentsListSize));
                throw new FullSectionsException(this, studentsCouldNotRegister);
            }

            //here we loop for the professors to create section for each one
            for (int i = 0; i <= professorsListSize - 1; i++) {
                Professor prof = professorTeachThisCourse.get(i);
                ArrayList<Student> tempStudentList = new ArrayList<>();

                try {
                    ArrayList<Student> tempListForRegistration = new ArrayList<>(studentsNeedThisCourse.subList(20 * i, 20 * (i + 1)));
                    tempStudentList.addAll(tempListForRegistration);
                } catch (IndexOutOfBoundsException e) {
                    try {
                        ArrayList<Student> tempListForRegistration = new ArrayList<>(studentsNeedThisCourse.subList(20 * i, studentsListSize));
                        tempStudentList.addAll(tempListForRegistration); }
                    catch (ClassCastException x) {
                        System.out.printf("%s has problem", this.name);
                    }
                }

                try {
                    Section section = new Section(this, prof, 20, sectionTime, Main.ClassDuration(sectionTime), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                } catch (StudentRegistrationConflictException e) {
                    tempStudentList = e.removeStudents(tempStudentList);
                    Section section = new Section(this, prof, 20, sectionTime, Main.ClassDuration(sectionTime), tempStudentList);
                    prof.addCurrentSections(section);
                    this.sections.add(section);
                }
            }
        }
    }

    public ArrayList<Section> getSections() {
        return sections;
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

    public String getCourseSym() {
        String Sym[] = this.name.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        return Sym[0];
    }

    // toString
    @Override
    public String toString() {
        return " Course: " + this.name + " Credits: " + this.credits + " Sections: " + getSectionsListAsString();
    }
}