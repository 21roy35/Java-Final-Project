package main;

import java.util.ArrayList;

public class StudentRegistrationConflictException extends Exception{
    private ArrayList<Student> students = new ArrayList<>();
    private Section section;
    public ArrayList<StudentRegistrationConflictException> allStudentRegistrationConflictExceptions = new ArrayList<>();

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
}
