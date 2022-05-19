package main;

import java.util.ArrayList;

import main.*;

public class Department {

    private String name;
    private ArrayList<Major> majors = new ArrayList<Major>();
    private  ArrayList<Professor>professor= new ArrayList <Professor>();
    private ArrayList <Student> Student =new ArrayList <Student>();
    public static ArrayList<Department> allDepartments = new ArrayList<Department>();


    public Department (String name , ArrayList<Major> majors , ArrayList<Professor> professors, ArrayList<Student> students) throws Exception{
    	
    	for(Department d : allDepartments) {
    		if(allDepartments.contains(d)) {
    			throw new Exception("Department already exists.");
    		}
    	}
    	
        this.name=name;
        for(Major m : majors) this.majors.add(m);
        for (int i=0; i<professor.size(); i++) {
            professor.add(professors.get(i));
        }
        this.Student=students;
        allDepartments.add(this);
    }
    public Department (String name , Major m , ArrayList<Professor> professors, ArrayList<Student> students) throws Exception{
    	
    	for(Department d : allDepartments) {
    		if(allDepartments.contains(d)) {
    			for(Major maj : this.majors) {
    				if(this.majors.contains(maj)) {
    					throw new Exception("Major already exists in department.");
    				}
    				else {
    					this.majors.add(m);
    				}
    			}
    			throw new Exception("Department already exists.");
    		}
    	}

        this.name=name;
        this.majors.add(m);
        for (int i=0; i<professor.size(); i++) {
            professor.add(professors.get(i));
        }
        this.Student=students;
        allDepartments.add(this);
    }

    public void setName(String name) {
        this.name=name;
    }

    public String  getName () {
        return name;
    }
    public static ArrayList<Department> getAllDepartments(){
    	return allDepartments;
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