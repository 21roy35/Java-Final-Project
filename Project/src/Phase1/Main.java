package Phase1;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class Main {

	static String CURRENT_YEAR = "2022";
	static int NUMBER_OF_YEARS = 5; // one cycle = 5 years
	static DecimalFormat _ID = new DecimalFormat("00000");
	static int LATEST_ID;
	public static ArrayList<Student> current_students = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		createDepartments();
		createGeneralDepartments();
		createStudentsAndProfessors(200,80);
		
		int YEAR_COUNT = 0;
		while(NUMBER_OF_YEARS > YEAR_COUNT) {
			startYear();
			YEAR_COUNT++;
		}

		int notCompletedStudents = 0;
		int numStu = 0;
		for(Department de: Department.allDepartments) {
			for (int i = 0; i <= de.getStudentList().size() - 1; i++) {
				Student student = de.getStudentList().get(i);
				numStu += 1;
				if (student.getCreditsCompleted() < 155) {
					notCompletedStudents += 1;
				}
			}
		}
		System.out.println(FullSectionsException.allFullSectionsExceptions.size());
		System.out.println(NoAvailableProfessorException.allNoAvailableProfessorExceptions.size());
		System.out.println(StudentRegistrationConflictException.allStudentRegistrationConflictExceptions.size());
		System.out.println(numStu);
		System.out.println(notCompletedStudents);

		MainGUI.main(null);
	}

	public static void createDepartments() {
		File dir = new File(System.getProperty("user.dir"));
		File[] data = new File(dir + "\\data").listFiles();
		Boolean exists = false;
		Department department = null;
		for(File major : data) {
			try {
				Major m = new Major(major);
				for(Department de: Department.allDepartments) {
					if(de.getName().substring(0,5).equals(m.getName().substring(0,5))) {
						exists=true;
					}
					else {

						exists=false;
					}
				}
				if(!exists) {
					department = new Department(m.getName().split(" ")[0], m, new ArrayList<Professor>(), new ArrayList<Student>());
				}
				else{
					department.addMajors(m);
				}
				// To be continued

			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("CreateMajors: Department already exists or major problem.");
			}
		}
	}

	public static void createGeneralDepartments() throws Exception {
		Department science = new Department("Science");
		Major Math = new Major("Math", "MATH");
		Major Chemistry = new Major("Chemistry", "CHEM");
		Major Biology = new Major("Biology", "BIO");
		Major Physics = new Major("Physics", "PHYS");
		Major Stats = new Major("Stats", "STAT");
		science.addMajors(Math, Chemistry, Biology, Physics, Stats);

		Department general = new Department("General");
		Major Arabic = new Major("Arabic", "ARAB");
		Major Islamic = new Major("Islamic", "ISLS");
		Major LANE = new Major("LANE", "LANE");
		Major LANF = new Major("LANF", "LANF");
		Major HIST = new Major("HIST", "HIST");
		Major GEOG = new Major("GEOG", "GEOG");
		Major IS = new Major("IS", "IS");
		Major SOC = new Major("SOC", "SOC");
		Major COM = new Major("COM", "COM");
		Major COMM = new Major("COMM", "COMM");
		Major PSY = new Major("PSY", "PSY");
		Major BL = new Major("BL", "BL");
		Major BLA = new Major("BLA", "BLA");
		Major CPIT = new Major("CPIT", "CPIT");
		Major ELIS = new Major("ELIS", "ELIS");
		general.addMajors(Arabic, Islamic, LANE, LANF, HIST, GEOG, IS, COM, COMM, PSY, SOC, BL, BLA, CPIT, ELIS);
	}

	private static void createStudentsAndProfessors(int numberOfStudents, int numberOfProfessors) throws Exception {
		//testing
		ArrayList<Student> departmentStudents = new ArrayList<Student>();
		ArrayList<Professor> departmentProfessors = new ArrayList<Professor>();

		for (int i = 0; i <= 6; i++) {
			Department department = Department.allDepartments.get(i);
			departmentStudents = newStudent(numberOfStudents, department);
			departmentProfessors = newProfessor(numberOfProfessors, department);
			System.out.println("\n-----------------------------------------------------");
			System.out.println("\nDepartment: "+department.getName()+"\nMajors: "+Department.allDepartments.get(i).allMajors());
			department.addStudentList(departmentStudents);
			department.addProfessorList(departmentProfessors);
			System.out.println("Students: " + department.getStudentList());
			System.out.println("Professers: " + department.getProfessorList());
		}//some files couldn't be read for some reason thats why not all majors are in the departments
		
	}

	public static ArrayList<String> StudentRandomNames(int number) {
	    String[] first = {"Mohammed", "Ahmed", "Ali", "Sami", "Omar", "Samer", "Nour", "Tamer", "Lina", "Dina", "Nada", "Hana", "Rania", "Yasmine", "Lamia", "Salma", "Aya", "Heba", "Mona", "Reem"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
	    Random rand = new Random();
	    ArrayList<String> result = new ArrayList<>();
	    for (int i = 0; i < number; i++) {
	      	if(i==20) {
	    		break; // only 20 preallocated names exist
	    	}
	        result.add( "[Student]: " + first[rand.nextInt(20)] + " " + last[rand.nextInt(20)]);
	    }
	    return result;
	}
	public static ArrayList<String> ProfRandomNames(int number) {
	    String[] first = {"Hadi", "Khalid", "Salem", "Fawaz", "Muhannad", "Moath", "Arwa", "Thamer", "Lujain", "Dana", "Hind", "Hanan", "Raneem", "Haifa", "Reem", "Abdulqudoos", "Saqer", "Sultan", "Hassan", "Ryan"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
	    Random rand = new Random();
	    //String[] result = new String[number];
	    ArrayList<String> result = new ArrayList<>();
	    for (int i = 0; i < number; i++) {
	    	if(i==20) {
	    		break; // only 20 preallocated names exist
	    	}
	        result.add("[Prof]: " + first[rand.nextInt(20)] + " " + last[rand.nextInt(20)]);
	    }
	    return result;
	}


	public static String randomID() throws Exception {
		if (LATEST_ID<99999) {
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
	public static ArrayList<Student> newStudent(int i, Department department) throws Exception {
		ArrayList<Student> students = new ArrayList<>();
		Random random = new Random();
		for (int j = 0; j < i; j++) {
			String name = StudentRandomNames(1).get(0);
			String ID = randomID();
			int majorListSize = department.getMajors().size();

			Major major = department.getMajors().get(random.nextInt(majorListSize));
			Student student = new Student(ID, name, major); //ahmed wut
			students.add(student);
		}
		return students;
	}

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

	public static ArrayList<Course> randomCourse(Department department) {
		ArrayList<Course> courses = new ArrayList<>();
		Major major = department.getMajors().get(new Random().nextInt(department.getMajors().size()));
		ArrayList<Course> randomTerm = major.getPlan().get(new Random().nextInt(major.getPlan().size()));
		Course randomCourse1 = randomTerm.get(new Random().nextInt(randomTerm.size()));
		Course randomCourse2 = randomTerm.get(new Random().nextInt(randomTerm.size()));
		Course randomCourse3 = randomTerm.get(new Random().nextInt(randomTerm.size()));

		if (randomCourse1.getName().contains(major.getSym()) && randomCourse2.getName().contains(major.getSym()) && randomCourse3.getName().contains(major.getSym())) {// need to make the Majors or the departments get a special words. like "EE"
			courses.add(randomCourse1);
			courses.add(randomCourse2);
			courses.add(randomCourse3);
			return courses;
		}
		else {
			return randomCourse(department);
		}
	}

	public static LocalTime randomClassTime() {
	    Random rand = new Random();
	    int hour = rand.nextInt(12) + 8;
	    int minute = rand.nextInt(2)+1;
	    switch(minute) {
			case 1:
				minute=00;
				break;
			case 2:
				minute = 30;
				break;
	    	default:
	    		minute = 30;
	    		break;
	    }
	    String frmt =  String.format("%02d:%02d", hour, minute);
	    return LocalTime.parse(frmt);
	}

	public static String randomClassDuration() {

	    Random rand = new Random();
	    int hour = rand.nextInt(12);
	    int minute = rand.nextInt(2)+1;
	    switch(minute) {
			case 1:
				minute=00;
				break;
			case 2:
				minute = 30;
				break;
	    	default:
	    		minute = 30;
	    		break;
	    }
	    String frmtTime =  String.format("%02d:%02d", hour, minute);
	    return frmtTime;
	}

	public static void startYear() {
		int Students = 300; //students per term
		try {
			current_students = Department.allDepartments.get(0).getStudentList();

			// pdateJList(current_students);

			System.out.println("Year Starting...");
			Thread.sleep(3000);

			for (Course course : Course.allCourses) {
				String courseID = course.getCourseSym();
				Department department = Department.getDepartmentFromID(courseID);
				try {
					course.createSections(department);
				} catch (NoAvailableProfessorException e) {

				}
				catch (FullSectionsException x) {

				}
			}

			for(Student student : current_students) {
				student.updateStudent();
			}

			System.out.println("Students graduated term 1");
	//		current_students = newStudent(Students);

			Thread.sleep(3000);

			for (Course course : Course.allCourses) {
				String courseID = course.getCourseSym();
				Department department = Department.getDepartmentFromID(courseID);
				try {
					course.createSections(department);
				} catch (NoAvailableProfessorException e) {

				}
				catch (FullSectionsException x) {

				}
			}

			for(Student student : current_students) {
				student.updateStudent();
			}

			System.out.printf("Students #%d graduated year #%s\n", current_students.size(), CURRENT_YEAR);

			int addYear = Integer.parseInt(CURRENT_YEAR) + 1;
			CURRENT_YEAR = String.valueOf(addYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
