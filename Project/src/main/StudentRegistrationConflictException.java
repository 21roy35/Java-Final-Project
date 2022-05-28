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
        for (int i = 0; i <= students.size() - 1; i++) {
            Student student = students.get(i);
            for (int r = 0; r <= Section.getAllSections().size() - 1; r++) {
                Section section = Section.getAllSections().get(r);
                Course course = this.section.getCourse();
                if (section.getCourse() == course) {
                    for (int s = 0; s <= course.getSections().size(); s++) {
                        Section section1 = course.getSections().get(s);
                        if (section1.getCapacity() < 20) {
                            section1.addStudents(student);
                            students.remove(student);
                            break;
                        }
                    }
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
}
