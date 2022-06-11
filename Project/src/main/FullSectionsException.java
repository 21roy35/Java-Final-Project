package main;

import java.util.ArrayList;

public class FullSectionsException extends Exception{
    private Course course;
    private ArrayList<Student> studentsCouldNotRegister = new ArrayList<>();
    public static ArrayList<FullSectionsException> allFullSectionsExceptions = new ArrayList<>();

    public FullSectionsException(Course course, ArrayList<Student> students) {
        if (!allFullSectionsExceptions.contains(this)) {
            this.course = course;
            this.studentsCouldNotRegister.addAll(students);
            allFullSectionsExceptions.add(this);
        }
    }

    public FullSectionsException(Course course, Student student) {
        if (!allFullSectionsExceptions.contains(this)) {
            this.course = course;
            this.studentsCouldNotRegister.add(student);
            allFullSectionsExceptions.add(this);
        }
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

    @Override
    public String toString() {
        return super.toString();
    }
}
