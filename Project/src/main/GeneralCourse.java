package main;

import java.time.LocalTime;
import java.util.ArrayList;

public class GeneralCourse extends Course implements GeneralCourseInterface{
    public GeneralCourse(String name, int credit, ArrayList<Course> prereqList) {
        super(name, credit, prereqList);
    }

    @Override
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
            if (time == sectionTime) {
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
                boolean profLimit = prof.getLimit() <= 12 - this.getCredits();
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
            if ((studentsListSize + professorsListSize - 1) /   professorsListSize > 50) { //first, we check if the professors are not enough
                //if they are not enough, we will throw FullSectionsException and add the student who could not register there
                int index = professorsListSize * 50;
                ArrayList<Student> studentsCouldNotRegister = (ArrayList<Student>) studentsNeedThisCourse.subList(index, studentsListSize);
                throw new FullSectionsException(this, studentsCouldNotRegister);
            }

            //here we loop for the professors to create section for each one
            for (int i = 0; i <= professorsListSize - 1; i++) {
                Professor prof = professorTeachThisCourse.get(i);
                ArrayList<Student> tempStudentList = new ArrayList<>();

                try {
                    ArrayList<Student> tempListForRegistration = (ArrayList<Student>) studentsNeedThisCourse.subList(50 * i, 50 * (i + 1));
                    tempStudentList.addAll(tempListForRegistration);
                } catch (IndexOutOfBoundsException e) {
                    try {
                        ArrayList<Student> tempListForRegistration = (ArrayList<Student>) studentsNeedThisCourse.subList(50 * i, studentsListSize);
                        tempStudentList.addAll(tempListForRegistration); }
                    catch (ClassCastException x) {
                        System.out.printf("%s has problem", this.getName());
                    }
                }

                try {
                    Section section = new Section(this, prof, 50, sectionTime, Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.getSections().add(section);
                } catch (StudentRegistrationConflictException e) {
                    tempStudentList = e.removeStudents(tempStudentList);
                    Section section = new Section(this, prof, 50, sectionTime, Main.randomClassDuration(), tempStudentList);
                    prof.addCurrentSections(section);
                    this.getSections().add(section);
                }
            }
        }
    }

    @Override
    public boolean collegeRequirement() {
        boolean collegeRequirement;
        int courseCapacity = this.getSections().get(0).getCapacity();
        collegeRequirement = courseCapacity == 50;
        return collegeRequirement;
    }
}
