import java.util.ArrayList;

import main.*;

public class Department {

    private String name;
    private Major major;
    private  ArrayList<Professor>professor= new ArrayList <Professor>();
    private ArrayList <Student> Student =new ArrayList <Student>();


    public Department (String name , Major major , ArrayList<Professor> professors, ArrayList<Student> students){
        this.name=name;
        this.major=major;
        for (int i=0; i<professor.size(); i++) {
            professor.add(professors.get(i));
        }
        this.Student=students;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String  getName () {
        return name;
    }

    public void addProfessorList (ArrayList<Professor>professors) {
        for (int i=0; i<professor.size(); i++) {
            professor.add(professors.get(i));
        }
    }


    public void removeProfessorList(ArrayList<Professor> professors) {

        for (int i=0; i<professor.size(); i++) {
            professor.remove(professors.get(i));
        }
    }

    public ArrayList<Professor> getProfessorList(){
        return this.professor;
    }

    public void addStudentList (ArrayList<Student> students) {
        for (int i=0; i<students.size(); i++) {
            students.add(students.get(i));
        }

    }
    public void removeStudentList(ArrayList<Student> students) {
        for (int i=0; i<students.size(); i++) {
            students.remove(students.get(i));
        }

    }

    public ArrayList<Student> getStudentList (){
        return this.Student;
    }


}