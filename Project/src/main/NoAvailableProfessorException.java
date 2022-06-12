package main;

import java.util.ArrayList;
/**
 * Date: June 12-2022
 * This is the NoAvailableProfessorException class extends Exception
 * @author Team1
 *
 */
public class NoAvailableProfessorException extends Exception{
    /**
     * this the course
     */
    private Course course;
    /**
     * this is a list of no available professor Exception
     */
    public static ArrayList<NoAvailableProfessorException> allNoAvailableProfessorExceptions = new ArrayList<>();

    /**
     * this is a constructor to initiate Course
     * @param course assign a value to the course
     */
    public NoAvailableProfessorException(Course course) {
        if(!allNoAvailableProfessorExceptions.contains(this)) {
            this.course = course;
            allNoAvailableProfessorExceptions.add(this);
        }
    }

    /**
     * this is a method to get the course
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * this is a method to set a course
     * @param course asign a value to the course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * this is a mthod that check for available professor
     *
     */
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
