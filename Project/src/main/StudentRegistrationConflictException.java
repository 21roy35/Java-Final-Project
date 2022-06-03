package main;

import java.util.ArrayList;

public class StudentRegistrationConflictException extends Exception{
    private ArrayList<Student> students = new ArrayList<>();
    private Section section;
    public static ArrayList<StudentRegistrationConflictException> allStudentRegistrationConflictExceptions = new ArrayList<>();

    public StudentRegistrationConflictException(ArrayList<Student> students, Section section) {
        this.students = students;
        this.section = section;
    }

    public StudentRegistrationConflictException(Student student, Section section) {
        this.students.add(student);
        this.section = section;
        allStudentRegistrationConflictExceptions.add(this);
    }

    public ArrayList<Student> removeStudents(ArrayList<Student> students) {
        for (int i = 0; i <= students.size() - 1; i++) {
            Student student = students.get(i);
            Course course = this.section.getCourse();
            for (int s = 0; s <= course.getSections().size() - 1; s++) {
                Section section = course.getSections().get(s);
                if (section.getStudentList().size() < section.getCapacity()) {
                    section.addStudents(student);
                    students.remove(student);
                    break;
                }
            }
        }
        students.removeAll(this.students);
        return students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void checkStudentsInException() {
        for (int i = 0; i <= students.size() - 1; i++) {
            Student student = students.get(i);
            Course course = this.section.getCourse();
            for (int s = 0; s <= course.getSections().size() - 1; s++) {
                Section section = course.getSections().get(s);
                if (section.getStudentList().size() < section.getCapacity()) {
                    section.addStudents(student);
                    students.remove(student);
                    break;
                }
            }
        }
        if (students.size() == 0) {
            allStudentRegistrationConflictExceptions.remove(this);
        }
    }
}
