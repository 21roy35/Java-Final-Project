package main;

import java.util.ArrayList;

/**
 * Date: June 11-2022
 * This is the FullSectionsException class that extends Exception class
 * @author Team1
 *
 */
public class FullSectionsException extends Exception{
    /**
     * this is the Exception's course
     */
    private Course course;
    /**
     * list of the students that could not register exceptions
     */
    private ArrayList<Student> studentsCouldNotRegister = new ArrayList<>();
    /**
     * list of all full sections exceptions
     */
    public static ArrayList<FullSectionsException> allFullSectionsExceptions = new ArrayList<>();

    /**
     * this is a constructor that takes course and students as an arguments
     * @param course the course value
     * @param students the list of students
     */
    public FullSectionsException(Course course, ArrayList<Student> students) {
        if (!allFullSectionsExceptions.contains(this)) {
            this.course = course;
            this.studentsCouldNotRegister.addAll(students);
            allFullSectionsExceptions.add(this);
        }
    }

    /**
     * this is a constructor that takes course and one student as an arguments
     * @param course the course value
     * @param student the student
     */
    public FullSectionsException(Course course, Student student) {
        if (!allFullSectionsExceptions.contains(this)) {
            this.course = course;
            this.studentsCouldNotRegister.add(student);
            allFullSectionsExceptions.add(this);
        }
    }

    /**
     * method to get course
     * @return course's course
     */
    public Course getCourse() {
        return course;
    }
    
    /**
     * method to set course
     * @param course the course value
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    
    /**
     * method to get students that could not register
     * @return students that could not register
     */
    public ArrayList<Student> getStudentsCouldNotRegister() {
        return studentsCouldNotRegister;
    }
    
    /**
     * method to set students that could not register
     * @param studentsCouldNotRegister the students that could not register list value
     */
    public void setStudentsCouldNotRegister(ArrayList<Student> studentsCouldNotRegister) {
        this.studentsCouldNotRegister = studentsCouldNotRegister;
    }

    /**
     * method to check if Students is already in course's sections or not
     */
    public void checkStudentsInException() {
        for (int i = 0; i <= studentsCouldNotRegister.size() - 1; i++) {
            Student student = studentsCouldNotRegister.get(i);
            for (int s = 0; s <= course.getSections().size() - 1; s++) {
                Section section = course.getSections().get(s);
                if (section.getStudentList().size() < section.getCapacity()) {
                    section.addStudents(student);
                    studentsCouldNotRegister.remove(student);
                    break;
                }
            }
        }
    }

    /**
     * method to check if section is empty or not 
     */
    public static void checkEmptyFullSectionsException() {
        ArrayList<FullSectionsException> tempList = new ArrayList<>();
        for (int i = 0; i <= allFullSectionsExceptions.size() - 1; i++) {
            FullSectionsException ex = allFullSectionsExceptions.get(i);
            if (ex.getStudentsCouldNotRegister().size() == 0) {
                ex.setCourse(null);
                tempList.add(ex);
            }
        }
        allFullSectionsExceptions.removeAll(tempList);
    }

    /**
     * toString method 
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
