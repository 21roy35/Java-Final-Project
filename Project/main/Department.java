package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Date: June 11-2022
 * This is the Department class
 * @author Team1
 *
 */
public class Department {
    /**
     * this is the Department's name
     */
    private String name;
    /**
     * this is the Department's majors list
     */
    private  ArrayList<Major> majors = new ArrayList<>();
    /**
     * this is the Department's professors list
     */
	private  ArrayList<Professor>professorList = new ArrayList<>();
    /**
     * this is the Department's students list
     */
    private ArrayList <Student> studentList = new ArrayList<>();
    /**
     * this is a list of all departments
     */
    public static ArrayList<Department> allDepartments = new ArrayList<>();

    /**
     * this is a constructor that takes name, majors, professors and students and as an arguments and throws Exception
     * the it takes a list of majors.
     * @param name initial name for Department
     * @param majors initial majors list for Department
     * @param professors initial professors list for Department
     * @param students initial students list for Department
     * @throws Exception might throw "Department already exists." exception
     */
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

    /**
     * this is a constructor that takes name, major, professors and students and as an arguments and throws Exception
     * the it takes only one major not a list
     * @param name initial name for Department
     * @param major initial major for Department
     * @param professors initial professors list for Department
     * @param students initial students list for Department
     * @throws Exception might throw "Department already exists." and "Major already exists in department." exceptions
     */
    public Department(String name, Major major, ArrayList<Professor> professors, ArrayList<Student> students) throws Exception {
        //the same construct but when the major is not a list
        if(allDepartments.contains(this)) {
            if(this.majors.contains(major)) { //instead of a loop, we only need an if statement
                throw new Exception("Major already exists in department.");
            }
            else {
                this.majors.add(major);
            }
            throw new Exception("Department already exists.");
        }
        else {
            this.majors.add(major);
        }

        this.name = name;
        this.professorList.addAll(professors);
        this.studentList.addAll(students);
        allDepartments.add(this);
    }

    /**
     * in this constructor, we only assign a name and a major as an arguments to the Department object
     * @param name initial name for Department
     * @param major initial major for Department
     * @throws Exception might throw "Department already exists." and "Major already exists in department." exceptions
     */
    public Department(String name, Major major) throws Exception {
        //in this constructor, we only assign a name and a major to the Department object
        if(allDepartments.contains(this)) {
            if(this.majors.contains(major)) {
                throw new Exception("Major already exists in department.");
            }
            else {
                this.majors.add(major);
            }
            throw new Exception("Department already exists.");
        }
        else {
            this.majors.add(major);
        }

        this.name = name;
        allDepartments.add(this);
    }

    /**
     * in this constructor, we only a name to the Department object
     * @param name initial name for Department
     */
    public Department(String name) {
        this.name = name;
    }

    /**
     * method to set name
     * @param name the name value
     */
    public void setName(String name) {
        this.name=name;
    }
    
    /**
     * method to get name
     * @return Department's name
     */
    public String getName() {
        return name;
    }

    /**
     * method to add multiple majors to majors list
     * @param majors multiple Majors
     */
    public void addMajors(Major... majors){
        Collections.addAll(this.majors, majors);
    }

    /**
     * method to get majors list
     * @return Department's majors list
     */
    public ArrayList<Major> getMajors() {
		return majors;
	}

    /**
     * method to add a list of Professors
     * @param professors list of Professors
     */
    public void addProfessorList (ArrayList<Professor> professors) {
        professorList.addAll(professors);
    }

    /**
     * method to remove a list of Professors
     * @param professors list of Professors
     */
    public void removeProfessorList(ArrayList<Professor> professors) {
        this.professorList.removeAll(professors);
    }

    /**
     * method to get Professors List
     * @return Department's Professor List
     */
    public ArrayList<Professor> getProfessorList(){
        return this.professorList;
    }

    /**
     * method to add a list of Students
     * @param students list of Students
     */
    public void addStudentList (ArrayList<Student> students) {
        this.studentList.addAll(students);
    }

    /**
     * method to add a list of Students
     * @param students list of Students
     */
    public void removeStudentList(ArrayList<Student> students) {
        this.studentList.removeAll(students);
    }

    /**
     * method to get Students List
     * @return Department's Students List
     */
    public ArrayList<Student> getStudentList (){
        return this.studentList;
    }

    /**
     * method to get All Departments List
     * @return All Departments list
     */
    public static ArrayList<Department> getAllDepartments(){
        return allDepartments;
    }

    /**
     * this method is for creating courses
     * @param name course's name
     * @param credits course's credits
     * @param prereqList course's prerequisites list
     * @return new course 
     */
    public static Course createCourse(String name, int credits, ArrayList<Course> prereqList) {
    	//this method is for creating courses
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

    /**
     * this method is for creating elective courses
     * @param name elective course's name
     * @param prereqList elective course's prerequisites list
     * @return new elective course
     */
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
    
    /**
     * this method is for creating university courses
     * @param name university course's name
     * @param prereqList university course's prerequisites list
     * @return new university course
     */
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
    
    /**
     * this method is for creating general courses
     * @param name general course's name
     * @param credits general course's credits
     * @param prereqList general course's prerequisites list
     * @return new general course
     */
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

    /**
     * this method is for creating a new section for a specific course
     * @param course the course of the section
     * @param prof the professor teaches in the section
     * @param capacity capacity of the section
     * @param time time of the section
     * @param duration duration of the section
     * @param students students for the section
     * @return new Section
     */
    public static Section createSectionForCourse(Course course, Professor prof, int capacity, LocalTime time, String duration, ArrayList<Student> students) {
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

    /**
     * this method generates sections for a specific course which will be called in main
     * @param course course for the section
     */
    public static void createSectionsInMain(Course course) {
        int i = 0;
        while (i < 2) {
            try {
                course.createSections();
                i++;
            } catch (NoAvailableProfessorException | FullSectionsException e) {
                course.addProfessors();
                i++;
            }
        }
    }
}