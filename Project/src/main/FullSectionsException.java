package main;

import java.util.ArrayList;

public class FullSectionsException extends Exception{
    private Course course;
    private ArrayList<Student> studentsCouldNotRegister = new ArrayList<>();
    public static ArrayList<FullSectionsException> allFullSectionsExceptions = new ArrayList<>();

    public FullSectionsException(Course course, ArrayList<Student> students) {
        this.course = course;
        this.studentsCouldNotRegister.addAll(students);
        allFullSectionsExceptions.add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Student> getStudentsCouldNotRegister() {
        return studentsCouldNotRegister;
    }

    public void setStudentsCouldNotRegister(ArrayList<Student> studentsCouldNotRegister) {
        this.studentsCouldNotRegister = studentsCouldNotRegister;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
