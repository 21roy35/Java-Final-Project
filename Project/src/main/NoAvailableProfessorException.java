package main;

import java.util.ArrayList;

public class NoAvailableProfessorException extends Exception{
    private Course course;
    public static ArrayList<NoAvailableProfessorException> allNoAvailableProfessorExceptions = new ArrayList<>();

    public NoAvailableProfessorException(Course course) {
        if(!allNoAvailableProfessorExceptions.contains(this)) {
            this.course = course;
            allNoAvailableProfessorExceptions.add(this);
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static void checkNoAvailableProfessorException() {
        ArrayList<NoAvailableProfessorException> tempList = new ArrayList<>();
        for (int i = 0; i <= allNoAvailableProfessorExceptions.size() - 1; i++) {
            NoAvailableProfessorException ex = allNoAvailableProfessorExceptions.get(i);
            for (int r = i + 1; r <= allNoAvailableProfessorExceptions.size() - 1; r++) {
                NoAvailableProfessorException exception = allNoAvailableProfessorExceptions.get(r);
                if (ex.getCourse().equals(exception.getCourse())) {
                    tempList.add(exception);
                }
            }
        }
        allNoAvailableProfessorExceptions.removeAll(tempList);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
