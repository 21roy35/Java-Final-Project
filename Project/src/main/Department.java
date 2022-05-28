package main;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.EnumSet;

public class Department {

    private String name;
    private ArrayList<String> code;
    private  ArrayList<Major> majors = new ArrayList<Major>();
	private  ArrayList<Professor>professor= new ArrayList <Professor>();
    private ArrayList <Student> studentList = new ArrayList <Student>();
    public static ArrayList<Department> allDepartments = new ArrayList<Department>();

    public enum ENG {EE, IE, AE, ChE, CE, ME, MINE, NE}

    public enum Science {PHYS, CHEM, BIO, MATH, STAT}

    public enum General {CPIT, COMM, ELIS, ARAB, ISLS, LANE, HIST, GEOG, IS, SOC, COM, PSY, BL, BLA}

    public Department (String name , ArrayList<Major> majors , ArrayList<Professor> professors, ArrayList<Student> students) throws Exception{
        if(allDepartments.contains(this)) {
            throw new Exception("Department already exists.");
        }

        this.name = name;

        for(Major m : majors) {
            this.majors.add(m);
        }

        for (int i=0; i<professor.size(); i++) {
            professor.add(professors.get(i));
        }

        this.studentList = students;
        allDepartments.add(this);
    }

    public Department (String name, Major m, ArrayList<Professor> professors, ArrayList<Student> students) throws Exception{
        if(allDepartments.contains(this)) {
            if(this.majors.contains(m)) {
                throw new Exception("Major already exists in department.");
            }
            else {
                this.majors.add(m);
            }
            throw new Exception("Department already exists.");
        }

        this.name = name;
        this.majors.add(m);

        for (int i = 0; i < professor.size(); i++) {
            professor.add(professors.get(i));
        }
        this.studentList = students;

        String ID = this.getENGDepartmentID();
        String code = Department.getENGDepartmentID(ID).name();
        this.code.add(code);
        allDepartments.add(this);
    }

    public Department (String name, ArrayList<String> codes) throws Exception{
        if(allDepartments.contains(this)) {
            throw new Exception("Department already exists.");
        }

        this.name = name;
        this.code = codes;

        allDepartments.add(this);
    }


    public ArrayList<Major> getMajors() {
		return majors;
	}

	public void addMajors(Major... majors){
		for(Major major : majors) {
			this.majors.add(major);
		}
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

    public ArrayList<String> getCode() {
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    public void addProfessorList (ArrayList<Professor>professors) {
        for (int i=0; i<professors.size(); i++) {
            professor.add(professors.get(i));
        }
    }


    public void removeProfessorList(ArrayList<Professor> professors) {

        for (int i=0; i<professors.size(); i++) {
            professor.remove(professors.get(i));
        }
    }

    public ArrayList<Professor> getProfessorList(){
        return this.professor;
    }

    public void addStudentList (ArrayList<Student> students) {
        for (int i=0; i<students.size(); i++) {
        	studentList.add(students.get(i));
        }

    }
    public void removeStudentList(ArrayList<Student> students) {
        for (int i=0; i<students.size(); i++) {
            studentList.remove(students.get(i));
        }
    }

    public ArrayList<Student> getStudentList (){
        return this.studentList;
    }

    public static Course createCourse(String name, int credits, ArrayList<Course> prereqList) {
        for (int i = 0; i <= Course.allCourses.size() - 1; i++) {
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) {
                return tempCourse;
            }
        }

        Course course = new Course(name, credits, prereqList);
        Course.allCourses.add(course);
        return course;
    }

    public static Elective createElective(String name, ArrayList<Course> prereqList) {
        for (int i = 0; i <= Course.allCourses.size() - 1; i++) {
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) {
                return (Elective) tempCourse;
            }
        }

        Elective elective = new Elective(name, prereqList);
        Course.allCourses.add(elective);
        return elective;
    }

    public static UniversityCourse createUniversityCourse(String name, ArrayList<Course> prereqList) {
        for (int i = 0; i <= Course.allCourses.size() - 1; i++) {
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) {
                return (UniversityCourse) tempCourse;
            }
        }

        UniversityCourse universityCourse = new UniversityCourse(name, prereqList);
        Course.allCourses.add(universityCourse);
        return universityCourse;
    }

    public ArrayList<Major> allMajors(){
    	return majors;
    }

    public static GeneralCourse createGeneralCourse(String name, int credits, ArrayList<Course> prereqList) {
        for (int i = 0; i <= Course.allCourses.size() - 1; i++) {
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) {
                return (GeneralCourse) tempCourse;
            }
        }

        GeneralCourse generalCourse = new GeneralCourse(name, credits, prereqList);
        Course.allCourses.add(generalCourse);
        return generalCourse;
    }

    public static Department getDepartmentFromID(String ID) {
        Department department = null;
        for (Department de : allDepartments) {
            ArrayList<String> departmentID = de.getCode();
            for (String id : departmentID) {
                if (id.equals(ID)) {
                    department = de;
                }
            }
        }
        return department;
    }

    public String getENGDepartmentID() {
        Major major = this.majors.get(0);
        Course course = major.getPlan().get(7).get(2);
        String ID = course.getCourseID();
        return ID;
    }

    public static ENG getENGDepartmentID(String ID) {
         return ENG.valueOf(ID);
    }

    public static Science getScienceDepartmentID(String ID) {
        return Science.valueOf(ID);
    }

    public static General getGeneralDepartmentID(String ID) {
        return General.valueOf(ID);
    }
}