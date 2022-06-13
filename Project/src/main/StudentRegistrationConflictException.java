package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: June 11-2022
 * This is the StudentRegistrationConflictException class that extends Exception class
 * @author Team1
 *
 */
public class StudentRegistrationConflictException extends Exception{
    /**
     * this is the Exception's list of students
     */
    private ArrayList<Student> students = new ArrayList<>();
    /**
     * this is the Exception's section
     */
    private Section section;
    /**
     * this is the students info 
     */
    public static Map<Student, Department> students_info = new HashMap<>();
    /**
     * this is the Exception's all student registration conflicts
     */
    public static ArrayList<StudentRegistrationConflictException> allStudentRegistrationConflictExceptions = new ArrayList<>();

    /**
     * this is a constructor that takes students and section as an arguments
     * @param students the list of students
     * @param section the section value
     */
    public StudentRegistrationConflictException(ArrayList<Student> students, Section section) {
        if (!allStudentRegistrationConflictExceptions.contains(this)) {
            this.students.addAll(students);
            this.section = section;
            for (Student stud : students) {
                if (!students_info.containsKey(stud)) {
                    students_info.put(stud, stud.getDepartment());
                }
            }
        }
    }

    /**
     * this is a constructor that takes one student and section as an arguments
     * @param student the student value
     * @param section the section value
     */
    public StudentRegistrationConflictException(Student student, Section section) {
        if (!allStudentRegistrationConflictExceptions.contains(this)) {
            this.students.add(student);
            this.section = section;
            allStudentRegistrationConflictExceptions.add(this);
            if (!students_info.containsKey(student)) {
                students_info.put(student, student.getDepartment());
            }
        }
    }

    /**
     *  this is a method to remove a list of students from the exception's list of students and return the new list
     * @param students the list of student
     * @return the new list of students
     */
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

    /**
     * method to get the list of students 
     * @return students that could not register
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * method to set the list of students
     * @param students the list of students value
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * @return
     */
    public Section getSection() {
        return section;
    }

    /**
     * @param section
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * 
     */
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
    }

    /**
     * 
     */
    public static void checkEmptyStudentRegistrationConflictException() {
        ArrayList<StudentRegistrationConflictException> tempList = new ArrayList<>();
        for (int i = 0; i <= allStudentRegistrationConflictExceptions.size() - 1; i++) {
            StudentRegistrationConflictException ex = allStudentRegistrationConflictExceptions.get(i);
            if (ex.getStudents().size() == 0) {
                tempList.add(ex);
                ex.setSection(null);
            }
        }
        allStudentRegistrationConflictExceptions.removeAll(tempList);
    }

    /**
     * 
     */
    public static void checkRepeatedStudentRegistrationConflictException() {
        ArrayList<StudentRegistrationConflictException> tempList = new ArrayList<>();
        for (int i = 0; i <= allStudentRegistrationConflictExceptions.size() - 1; i++) {
            StudentRegistrationConflictException ex = allStudentRegistrationConflictExceptions.get(i);
            for (int r = i + 1; r <= allStudentRegistrationConflictExceptions.size() - 1; r++) {
                StudentRegistrationConflictException exception = allStudentRegistrationConflictExceptions.get(r);
                if (ex.getSection().equals(exception.getSection())) {
                    tempList.add(exception);
                }
            }
        }
        allStudentRegistrationConflictExceptions.removeAll(tempList);
    }
}
