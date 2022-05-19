package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {

	//lol
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


	public static void createMajors() {
		File dir = new File(System.getProperty("user.dir"));
		File[] data = new File(dir + "\\data").listFiles();
		for(File major :data) {
		try {
			
			Major m = new Major(major);
			for(Department de : Department)
			//Department department = new Department(major.getName(), m, ProfRandomNames(3), StudentRandomNames(20));
			// To be continued
			
		} catch (FileNotFoundException e) {
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
		
		createMajors();
//		 Professor pickedProfessor = this.professors.get((new Random().nextInt(this.professors.size())));
//		 Section newSection = new Section(this, pickedProfessor, 20, this.duration, this.);
		

		// Section section1 = new Section();
		// section1.addStudents();
		//System.out.println(section1);
	//	File file = new File("C:\\Users\\User\\git\\Java-Final-Project\\data\\Aerospace_ENG.txt");
	//	Major aero = new Major(file);
	//	 System.out.println(aero);
//		 Professor hussain = new Professor("1","Hussain");
//		 hussain.addCurrentSections(section1,section2);
//		 System.out.println(hussain.currentSections);
//		 hussain.addCurrentSections(section3,section4,section5,section6);
//		 System.out.println(hussain.currentSections);
//		 hussain.removeCurrentSections(section4,section3);
//		 System.out.println(hussain.currentSections);
//		 System.out.println(hussain.getLimit());
		 
//		 Student ahmed = new Student("1","Ahmed", computerEngineering,);

	}
}
