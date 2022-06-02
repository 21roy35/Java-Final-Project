package main;

import java.util.ArrayList;

public class NoAvailableProfessorException extends Exception{
    private Course course;
    public static ArrayList<NoAvailableProfessorException> allNoAvailableProfessorExceptions = new ArrayList<>();

    public NoAvailableProfessorException(Course course) {
        this.course = course;
        allNoAvailableProfessorExceptions.add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
