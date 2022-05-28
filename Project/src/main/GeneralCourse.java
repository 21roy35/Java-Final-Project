package main;

import java.time.LocalTime;
import java.util.ArrayList;

public class GeneralCourse extends Course implements GeneralCourseInterface{
    public GeneralCourse(String name, int credit, ArrayList<Course> prereqList) {
        super(name, credit, prereqList);
    }

    @Override
    public void createSections(Department department) throws Exception {
        //public Section(Professor professor, LocalTime time)
        //Section(d);
        //Section creation depends on: Student.neededCourses(), Course.professors, Major.plan.get(term).sectionloop.(onlyDepartmentCourses),

        String departmentID = this.getName().substring(0, 2);
        ArrayList<Course> term = Major.getTerm(department, this);
        ArrayList<LocalTime> times = new ArrayList<>();
        ArrayList<Course> removeList = new ArrayList<>();

        for (int i = 0; i <= term.size() - 1; i++) {
            Course course = term.get(i);
            String courseID = course.getName().substring(0, 2);
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
            if (tempCourses.contains(this) & prof.getLimit() <= 12 - this.getCredits()) {
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
                    tempStudentList.addAll((ArrayList<Student>) studentsNeedThisCourse.subList(50 * i, 50 * (i + 1)));
                } catch (IndexOutOfBoundsException e) {
                    tempStudentList.addAll((ArrayList<Student>) studentsNeedThisCourse.subList(50 * i, studentsNeedThisCourseSize));
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
        if (this.getSections().get(0).getCapacity() == 50 ) {
            collegeRequirement = true;
        }
        else {
            collegeRequirement = false;
        }

        return collegeRequirement;
    }
}
