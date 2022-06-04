package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Department {
    private String name;
    private  ArrayList<Major> majors = new ArrayList<>();
	private  ArrayList<Professor>professorList = new ArrayList<>();
    private ArrayList <Student> studentList = new ArrayList<>();
    public static ArrayList<Department> allDepartments = new ArrayList<>();

    public Department(String name, ArrayList<Major> majors, ArrayList<Professor> professors, ArrayList<Student> students) throws Exception {
        if(allDepartments.contains(this)) { //we first check if the department exists
            for (Major m : majors) { //we loop the major to check if they exist in the department
                if(this.majors.contains(m)) {
                    throw new Exception("Major already exists in department.");
                }
                else {
                    this.majors.add(m);
                }

                throw new Exception("Department already exists.");
            }
        }
        else {
            this.majors.addAll(majors);
        }

        this.name = name; //assign name
        this.professorList.addAll(professors); //add professors
        this.studentList.addAll(students); //add students
        allDepartments.add(this); //we add the object to the allDepartments list
    }

    public Department(String name, Major m, ArrayList<Professor> professors, ArrayList<Student> students) throws Exception {
        //the same construct but when the major is not a list
        if(allDepartments.contains(this)) {
            if(this.majors.contains(m)) { //instead of a loop, we only need an if statement
                throw new Exception("Major already exists in department.");
            }
            else {
                this.majors.add(m);
            }
            throw new Exception("Department already exists.");
        }
        else {
            this.majors.add(m);
        }

        this.name = name;
        this.professorList.addAll(professors);
        this.studentList.addAll(students);
        allDepartments.add(this);
    }

    public Department(String name, Major m) throws Exception {
        //in this constructor, we only assign a name and a major to the Department object
        if(allDepartments.contains(this)) {
            if(this.majors.contains(m)) {
                throw new Exception("Major already exists in department.");
            }
            else {
                this.majors.add(m);
            }
            throw new Exception("Department already exists.");
        }
        else {
            this.majors.add(m);
        }

        this.name = name;
        allDepartments.add(this);
    }

    public Department(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void addMajors(Major... majors){
        Collections.addAll(this.majors, majors);
    }

    public ArrayList<Major> getMajors() {
		return majors;
	}

    public void addProfessorList (ArrayList<Professor> professors) {
        professorList.addAll(professors);
    }

    public void removeProfessorList(ArrayList<Professor> professors) {
        this.professorList.removeAll(professors);
    }

    public ArrayList<Professor> getProfessorList(){
        return this.professorList;
    }

    public void addStudentList (ArrayList<Student> students) {
        this.studentList.addAll(students);
    }

    public void removeStudentList(ArrayList<Student> students) {
        this.studentList.removeAll(students);
    }

    public ArrayList<Student> getStudentList (){
        return this.studentList;
    }

    public static ArrayList<Department> getAllDepartments(){
        return allDepartments;
    }

    public static Course createCourse(String name, int credits, ArrayList<Course> prereqList) { //this method is for creating courses
        Course course = null; //null course

        for (int i = 0; i <= Course.allCourses.size() - 1; i++) { //we first check if the courses exists
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) { //compare the course with the course name
                course = tempCourse; //we assign course with exiting course
                break;               //and break the loop
            }
        }

        if (course == null) { //if course does not exist, we create new course and add to allCourses
            course = new Course(name, credits, prereqList);
            Course.allCourses.add(course);
        }
        return course;
    }

    public static Course createElective(String name, ArrayList<Course> prereqList) {
        Course elective = null; //null elective, we define it as Course type because the Elective object can be cast as course

        for (int i = 0; i <= Course.allCourses.size() - 1; i++) { //we first check if the courses exists
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) { //compare the course with the course name
                elective = tempCourse; //we assign course with exiting course
                break;                 //and break the loop
            }
        }

        if (elective == null) { //if elective does not exist, we create new course and add to allCourses
            elective = new Elective(name, prereqList);
            Course.allCourses.add(elective);
        }
        return elective;
    }

    public static Course createUniversityCourse(String name, ArrayList<Course> prereqList) {
        Course universityCourse = null; //null universityCourse, we define it as Course type because the UniversityCourse object can be cast as course

        for (int i = 0; i <= Course.allCourses.size() - 1; i++) { //we first check if the courses exists
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) { //compare the course with the course name
                universityCourse = tempCourse; //we assign course with exiting course
                break;                 //and break the loop
            }
        }

        if (universityCourse == null) { //if university course does not exist, we create new course and add to allCourses
            universityCourse = new UniversityCourse(name, prereqList);
            Course.allCourses.add(universityCourse);
        }
        return universityCourse;
    }

    public static Course createGeneralCourse(String name, int credits, ArrayList<Course> prereqList) {
        Course generalCourse = null; //null generalCourse, we define it as Course type because the GeneralCourse object can be cast as course

        for (int i = 0; i <= Course.allCourses.size() - 1; i++) { //we first check if the courses exists
            Course tempCourse = Course.allCourses.get(i);
            if (tempCourse.getName().equals(name)) { //compare the course with the course name
                generalCourse = tempCourse; //we assign course with exiting course
                break;               //and break the loop
            }
        }

        if (generalCourse == null) { //if general course does not exist, we create new course and add to allCourses
            generalCourse = new GeneralCourse(name, credits, prereqList);
            Course.allCourses.add(generalCourse);
        }
        return generalCourse;
    }

    public static Section createSection(Course course, Professor prof, int capacity, LocalTime time, String duration, ArrayList<Student> students) throws Exception {
        boolean thereIsNOException = false;
        Section section = null;
        while (!thereIsNOException) {
            try {
                section = new Section(course, prof, capacity, time, duration, students);
                prof.addCurrentSections(section);
                course.getSections().add(section);
                thereIsNOException = true;
            } catch (StudentRegistrationConflictException e) {
                students = e.removeStudents(students);
            }
        }
        return section;
    }
}