package main;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
/**
 * Date: June 12-2022
 * This is the Main  class
 * @author Team1
 *
 */
public class Main {
    /**
     * this is current year
     */
    static String CURRENT_YEAR = "2022";
    /**
     * this the number of years
     */
    static int NUMBER_OF_YEARS = 5;
    /**
     * this is the limits of  ID
     */
	static DecimalFormat _ID = new DecimalFormat("00000");
    /**
     * this is latest ID
     */
    static int LATEST_ID;
    /**
     *  this is number of conflect
     */
    static int conflictedStudents = 0;
    /**
     * this is current students list
     */
    public static ArrayList<Student> current_students = new ArrayList<>();
    /**
     * this is current professor list
     */
	public static ArrayList<Professor> current_professors = new ArrayList<>();

    /**
     * this is the main method
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		createDepartments();

		int YEAR_COUNT = 0;
		while(NUMBER_OF_YEARS > YEAR_COUNT) {
			startYear();
			YEAR_COUNT++;
		}

		NoAvailableProfessorException.checkNoAvailableProfessorException();
		StudentRegistrationConflictException.checkStudentRegistrationConflictException();

		System.out.println(FullSectionsException.allFullSectionsExceptions.size());
		System.out.println(NoAvailableProfessorException.allNoAvailableProfessorExceptions.size());
		System.out.println(StudentRegistrationConflictException.allStudentRegistrationConflictExceptions.size());

		MainGUI.main(null);
		System.out.println(getConflictInfo());
	}

    /**
     * this is a method to create a department
     */
	public static void createDepartments() {
		File dir = new File(System.getProperty("user.dir")); //we want to get the directory for the data of the major plans
		File[] data = new File(dir + "\\data").listFiles(); //here we put the major plans files into an array
		boolean exists = false;
		Department department = null;

		assert data != null;
		for(File major : data) { //this loop is used to create departments and their major plans.
			try {
				Major m = new Major(major); //we create the major first
				for(Department de: Department.allDepartments) { //we loop on allDepartments to see if the major's department exits
					String departmentName = de.getName().substring(0,5);
					String majorName = m.getName().substring(0,5);
					if (departmentName.equals(majorName)) { //we see if the major belongs to the specific department
						exists = true;
						department = de;
						break;
					}
					else {
						exists = false;
					}
				}

				if(exists) { //if department exits, we only add major
					department.addMajors(m);
				}
				else{ //if department does not exist, we make new department and add major
					String majorName = m.getName();
					String departmentName = majorName.split(" ")[0];
					department = new Department(departmentName, m);
				}
			} catch (Exception e) {
				System.out.println("CreateMajors: Department already exists or major problem.");
			}
		}
	}

    /**
     * this is a method to create a students & professor
     * @param numberOfStudents
     * @param numberOfProfessors
     * @throws Exception
     */
	private static void createStudentsAndProfessors(int numberOfStudents, int numberOfProfessors) throws Exception {
		ArrayList<Student> departmentStudents;
		ArrayList<Professor> departmentProfessors;

		for (int i = 0; i <= Department.allDepartments.size() - 1; i++) {
			Department department = Department.allDepartments.get(i);
			departmentStudents = newStudent(numberOfStudents, department);
			departmentProfessors = newProfessor(numberOfProfessors, department);
			department.addStudentList(departmentStudents);
			department.addProfessorList(departmentProfessors);
		}
	}

    /**
     * this is a method for student random names list
     * @param number
     * @return result
     */
	public static ArrayList<String> StudentRandomNames(int number) {
	    String[] first = {"Mohammed", "Ahmed", "Ali", "Sami", "Omar", "Samer", "Nour", "Tamer", "Lina", "Dina", "Nada", "Hana", "Rania", "Yasmine", "Lamia", "Salma", "Aya", "Heba", "Mona", "Reem"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
	    Random rand = new Random();
	    ArrayList<String> result = new ArrayList<>();
	    for (int i = 0; i < number; i++) {
	        result.add( "[Student]: " + first[rand.nextInt(20)] + " " + last[rand.nextInt(20)]);
	    }
	    return result;
	}

    /**
     *  this is a method for professor names list
     * @param number
     * @return result
     */
	public static ArrayList<String> ProfRandomNames(int number) {
	    String[] first = {"Hadi", "Khalid", "Salem", "Fawaz", "Muhannad", "Moath", "Arwa", "Thamer", "Lujain", "Dana", "Hind", "Hanan", "Raneem", "Haifa", "Reem", "Abdulqudoos", "Saqer", "Sultan", "Hassan", "Ryan"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
		Random rand = new Random();
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			result.add( "[Professor]: " + first[rand.nextInt(20)] + " " + last[rand.nextInt(20)]);
		}
		return result;
	}

    /**
     * this is a method a generate a random ID
     * @return return value
     * @throws Exception
     */
	public static String randomID() throws Exception {
		if (LATEST_ID < 99999) {
		String Latest_ID_UPDT;
		Latest_ID_UPDT = _ID.format(LATEST_ID);
		LATEST_ID+=1;
		String return_val;
		return_val = CURRENT_YEAR.substring(2).concat(Latest_ID_UPDT);
		return return_val;
		}
		else {
			throw new Exception("randomID exception. LATEST_ID max limit reached.");
		}
	}

    /**
     * this is a method to crate list of new students
     * @param i number of student
     * @param department
     * @return students
     * @throws Exception
     */
	public static ArrayList<Student> newStudent(int i, Department department) throws Exception {
		ArrayList<Student> students = new ArrayList<>();
		Random random = new Random();
		for (int j = 0; j < i; j++) {
			String name = StudentRandomNames(1).get(0);
			String ID = randomID();
			int majorListSize = department.getMajors().size();
			Major major = department.getMajors().get(random.nextInt(majorListSize));
			Student student = new Student(ID, name, major);
			students.add(student);
		}
		return students;
	}

    /**
     * this is a method to create a list of new professor
     * @param i
     * @param department
     * @return
     * @throws Exception
     */
	public static ArrayList<Professor> newProfessor(int i, Department department) throws Exception {
		ArrayList<Professor> profs = new ArrayList<>();
		for(int j = 0; j < i; j++) {
			String name = ProfRandomNames(1).get(0);
			String ID = randomID();
			ArrayList<Course> courses = randomCourse(department);
			Professor prof = new Professor(ID, name, courses);
			profs.add(prof);
		}
		return profs;
	}

    /**
     *  this is a method to craete a random courses list
     * @param department
     * @return
     */
	public static ArrayList<Course> randomCourse(Department department) {
		ArrayList<Course> courses = new ArrayList<>();
		Random rand = new Random();

		try {
			int randomMajor = rand.nextInt(department.getMajors().size());
			Major major = department.getMajors().get(randomMajor);

			int yearCount = Integer.parseInt(CURRENT_YEAR) - 2021;
			int randomTerm1 = rand.nextInt(yearCount);
			int randomTerm2 = rand.nextInt(yearCount +  2);
			ArrayList<Course> Term1 = major.getPlan().get(randomTerm1);
			ArrayList<Course> Term2 = major.getPlan().get(randomTerm2);

			int randomCourse1 = rand.nextInt(Term1.size());
			int randomCourse2 = rand.nextInt(Term1.size());
			int randomCourse3 = rand.nextInt(Term2.size());
			int randomCourse4 = rand.nextInt(Term2.size());

			Course Course1 = Term1.get(randomCourse1);
			Course Course2 = Term1.get(randomCourse2);
			Course Course3 = Term2.get(randomCourse3);
			Course Course4 = Term2.get(randomCourse4);

			courses.add(Course1);
			courses.add(Course2);
			courses.add(Course3);
			courses.add(Course4);
			return courses;

		} catch (IllegalArgumentException e) {
			return randomCourse(department);
		}
	}

    /**
     * this is a method to initiate time
     * @return time
     */
	public static LocalTime randomClassTime() {
		Random rand = new Random();
		int hour = rand.nextInt(10) + 8;
		int minute = rand.nextInt(2);
		int numOfDays = rand.nextInt(2) + 2;

		if (hour%2 == 0 & numOfDays == 2) {
			switch(minute) {
				case 0:
					break;
				case 1:
					minute = 30;
					break;
			}
		}
		else {
			minute = 0;
		}

		String format = String.format("%02d:%02d:%02d", hour, minute, numOfDays);
		LocalTime time = LocalTime.parse(format);
		return time;
	}

    /**
     *  this is a method to initiate class duration
     * @param time
     * @return
     */
	public static String ClassDuration(LocalTime time) {
	    int numOfDays = time.getSecond();
		String duration;
		if (numOfDays == 2) {
			duration = "50";
		}
		else {
			duration = "75";
		}
		return duration;
	}

	/**
	 * @return a random Section ID
	 */
	public static String randomSectionID() {
		return String.valueOf(new Random(7).nextInt()+10000);
	}

	public static String getConflictInfo() {
		int c_department = 0;
		int c_students = 0;
		int c_majors = 0;
		Map<Major, Integer> majorsMap = new HashMap<>();
		ArrayList<Department> departments = new ArrayList<>();
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Major> majors = new ArrayList<>();
		
		for (Entry<Student, Department> entry : main.StudentRegistrationConflictException.students_info.entrySet()) {

			if(!departments.contains(entry.getValue())) {
				departments.add(entry.getValue());
				c_department+=1;
			}

			if(!students.contains(entry.getKey())) {
				students.add(entry.getKey());
				c_students+=1;
			}
			
			if(!majors.contains(entry.getKey().getMajor())) {
				majors.add(entry.getKey().getMajor());
				c_majors+=1;
			}
		
		}
		//c_students = main.StudentRegistrationConflictException.students_info.size();
		
		int counter = 0;
		for(Major m: majors) {
			for(Student stud : students) {
				if(m.equals(stud.getMajor())) {
					counter++;
				}
			}
			majorsMap.put(m, counter);
			counter=0;
		}
		conflictedStudents = c_students;

		return String.format("Conflicts found : \n 1- Department(s) affected: %d \n 2- Major(s) affected: %d \n 3- Student(s) affected: %d", c_department, c_majors, c_students);
	}
	
	public static String getConflictInfo(Major majorParam) {
		int c_department = 0;
		int c_students = 0;
		int c_majors = 0;
		Map<Major, Integer> majorsMap = new HashMap<>();
		ArrayList<Department> departments = new ArrayList<>();
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Major> majors = new ArrayList<>();
		
		for (Entry<Student, Department> entry : main.StudentRegistrationConflictException.students_info.entrySet()) {

			if(!departments.contains(entry.getValue())) {
				departments.add(entry.getValue());
				c_department+=1;
			}

			if(!students.contains(entry.getKey())) {
				students.add(entry.getKey());
				c_students+=1;
			}
			
			if(!majors.contains(entry.getKey().getMajor())) {
				majors.add(entry.getKey().getMajor());
				c_majors+=1;
			}
		
		}
		
		int counter = 0;
		for(Major m: majors) {
			for(Student stud : students) {
				if(m.equals(stud.getMajor())) {
					counter++;
				}
			}
			majorsMap.put(m, counter);
			counter=0;
		}
		
		int NumInMajor;
		NumInMajor = majorsMap.get(majorParam);

		return String.valueOf(NumInMajor);
	}

	public static void startYear() {
		try {
			createStudentsAndProfessors(100,20);

			for (Department de : Department.allDepartments) {
				ArrayList<Student> departmentStudentList = de.getStudentList();
				ArrayList<Professor> departmentProfessorList = de.getProfessorList();
				current_students.addAll(departmentStudentList);
				current_professors.addAll(departmentProfessorList);
			}
			System.out.println("Year Starting...\n---------------------------------------------");

			updateTerm();
			System.out.println("Students graduated term 1");

			updateTerm();
			System.out.printf("Students #%d graduated year #%s\n", current_students.size(), CURRENT_YEAR);
			System.out.printf("Professor count this year: %d\n", current_professors.size());

			int addYear = Integer.parseInt(CURRENT_YEAR) + 1;
			CURRENT_YEAR = String.valueOf(addYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateTerm() {
		for (Course course : Course.allCourses) {
			Department.createSectionsInMain(course);
		}

		for (StudentRegistrationConflictException e : StudentRegistrationConflictException.allStudentRegistrationConflictExceptions) {
			e.checkStudentsInException();
		}
		StudentRegistrationConflictException.checkEmptyStudentRegistrationConflictException();

		for (FullSectionsException e : FullSectionsException.allFullSectionsExceptions) {
			e.checkStudentsInException();
		}
		FullSectionsException.checkEmptyFullSectionsException();

		for (Student student : current_students) {
			student.updateStudent();
		}
		
		for(Professor professor : current_professors) {
			professor.updateProfessor();
		}

		for (Course course : Course.allCourses) {
			course.removeSections();
		}

		for (Section section : Section.sections) {
			section.endTerm();
		}
		Section.checkEmptySections();
	}
}
