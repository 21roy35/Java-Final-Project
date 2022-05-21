package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {

	
	static String CURRENT_YEAR = "2022";
	static int numberOfYears;
	static DecimalFormat _ID = new DecimalFormat("00000");
	static int LATEST_ID;
	
	
	public static ArrayList<String> StudentRandomNames(int number) {
	    String[] first = {"Mohammed", "Ahmed", "Ali", "Sami", "Omar", "Samer", "Nour", "Tamer", "Lina", "Dina", "Nada", "Hana", "Rania", "Yasmine", "Lamia", "Salma", "Aya", "Heba", "Mona", "Reem"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
	    Random rand = new Random();
	    ArrayList<String> result = new ArrayList<String>();
	    for (int i = 0; i < number; i++) {
	      	if(i==20) {
	    		break; // only 20 preallocated names exist
	    	}
	        result.add( "[Student]: " + first[rand.nextInt(number)] + " " + last[rand.nextInt(number)]);
	    }
	    return result;
	}
	public static ArrayList<String> ProfRandomNames(int number) {
	    String[] first = {"Hadi", "Khalid", "Salem", "Fawaz", "Muhannad", "Moath", "Arwa", "Thamer", "Lujain", "Dana", "Hind", "Hanan", "Raneem", "Haifa", "Reem", "Abdulqudoos", "Saqer", "Sultan", "Hassan", "Ryan"};
	    String[] last = {"Alqahtani", "Alotaibi", "Alghamdi", "Alyami", "Alshehri", "Alzahrani", "Aldossari", "Alamri", "Alsaggaf", "Alabdullah", "Aldakheil", "Aldhari", "Alrufaydi", "Almagrabi", "Alasais", "Alhabshi", "Altamimi", "Almutari", "Alkodry", "Alsulami"};
	    Random rand = new Random();
	    //String[] result = new String[number];
	    ArrayList<String> result = new ArrayList<String>();
	    for (int i = 0; i < number; i++) {
	    	if(i==20) {
	    		break; // only 20 preallocated names exist
	    	}
	        result.add("[Prof]: " + first[rand.nextInt(number)] + " " + last[rand.nextInt(number)]);
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
	public static Student newStudent() throws Exception {
		String name = StudentRandomNames(1).get(0);
		String ID = randomID();
		Major major = Major.getAllMajors().get(new Random().nextInt(Major.getAllMajors().size()));
		ArrayList<Course> coursesC = new ArrayList<>();
		Student student = new Student(name, ID, major, coursesC);
		return student;
	}
	
	public static Professor newProfessor() throws Exception {
		String name = ProfRandomNames(1).get(0);
		String ID = randomID();
	    Section section = new Section(); // needs editing
		ArrayList<Section> SectionsC = new ArrayList<Section>();
		SectionsC.add(section);
		Professor prof = new Professor(name, ID, SectionsC);
		return prof;
	}


	public static void createMajors() {
		File dir = new File(System.getProperty("user.dir"));
		File[] data = new File(dir + "\\data").listFiles();
		for(File major :data) {
		try {
			
			Major m = new Major(major);
			//for(Department de : Department)
			ArrayList<Professor> profs = new ArrayList<>();
			profs.add(newProfessor());
			ArrayList<Student> students = new ArrayList<>();
			students.add(newStudent());
			Department department = new Department(major.getName(), m, profs, students);
			// To be continued
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static void createCourses() {
		
	}
	 public static void createSections(Course course, Department department) {}
	 //Course course, Professor professor, int capacity, LocalTime time, LocalTime duration,ArrayList<Student> students, String sectionid)
	 Section newSection = new Section();
	 
	 
	 //	public Section(Professor professor, LocalTime time)
	 //Section(d);
	 // Section creation depends on: Student.neededCourses(), Course.professors, Major.plan.get(term).sectionloop.(onlyDepartmentCourses), 
	 
	 //Course course, Professor professor, int capacity, LocalTime time, LocalTime duration,ArrayList<Student> students, String sectionid)
		/*
		 * for (Professor prof : this.professors) { // for each professor, a new section
		 * will be created this.sections.add( this, prof, 20, LocalTime.now(), // time
		 * should be defined in main method to avoid conflicts, LocalTime.of(00, 50), //
		 * ignore hour field, mins will be used // students not defined here. // this
		 * method should go to the main method (in the simulation)
		 */		

	public static void main(String[] args) throws FileNotFoundException {

//		createMajors();
//		 Professor pickedProfessor = this.professors.get((new Random().nextInt(this.professors.size())));
//		 Section newSection = new Section(this, pickedProfessor, 20, this.duration, this.);
		


	}
}
