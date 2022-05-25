package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {

	static String CURRENT_YEAR = "2022"; 
	static int NUMBER_OF_CYCLES; // one cycle = 5 years
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
	        result.add( "[Student]: " + first[rand.nextInt(20)] + " " + last[rand.nextInt(20)]);
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
	public static ArrayList<Student> newStudent(int i) throws Exception {
		ArrayList<Student> students = new ArrayList<>();
		for (int j = 0; j < i; j++) {
		String name = StudentRandomNames(1).get(0);
		String ID = randomID();
		Major major = Major.getAllMajors().get(new Random().nextInt(Major.getAllMajors().size()));
		ArrayList<Course> coursesC = new ArrayList<>();
		Student student = new Student(name, ID, major, coursesC, major.createPlanForStudent()); //ahmed wut
		students.add(student);
		}
		return students;
	}
	
	public static ArrayList<Professor> newProfessor(int i) throws Exception {
		ArrayList<Professor> profs = new ArrayList<>();
		for(int j = 0; j < i; j++) {
		String name = ProfRandomNames(1).get(0);
		String ID = randomID();
	    Section section = null; // needs editing
		ArrayList<Section> SectionsC = new ArrayList<Section>();
		SectionsC.add(section);
		Professor prof = new Professor(name, ID, SectionsC);
		profs.add(prof);
		}
		return profs;
	}


	public static void createMajors() {
		File dir = new File(System.getProperty("user.dir"));
		File[] data = new File(dir + "\\data").listFiles();
		for(File major :data) {
		try {
			
			Major m = new Major(major);
			Department department = new Department(major.getName(), m, newProfessor(20), newStudent(200));
			// To be continued
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	public static String randomClassTime() { 
	    
	    Random rand = new Random(); 
	    int hour = rand.nextInt(12) + 8; 
	    int minute = rand.nextInt(2)+1;
	    System.out.println(minute);
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
	    
	    return String.format("%02d:%02d", hour, minute); 
	}
	
	public String randomClassDuration() { 
	    
	    Random rand = new Random(); 
	    int hour = rand.nextInt(12); 
	    int minute = rand.nextInt(2)+1;
	    System.out.println(minute);
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
	    
	    return String.format("%02d:%02d", hour, minute); 
	}
	public static String randomSectionID() {
		return String.valueOf(new Random(7).nextInt()+10000);
	}
	public static void createCoursesForStudent(Student s) {
	}
	 public static void createSections(Course course) {
	 //Course course, Professor professor, int capacity, LocalTime time, LocalTime duration,ArrayList<Student> students, String sectionid)
	int capacity = new Random().nextInt(19);
	 if(course.getCredits()==4) {

	 try {
		Section newSection = new Section(course, newProfessor(1).get(0), capacity, LocalTime.parse(randomClassTime()), "100", newStudent(20), randomSectionID());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 } else if(course.getCredits()==2) {
			try {
				Section newSection = new Section(course, newProfessor(1).get(0), capacity, LocalTime.parse(randomClassTime()), "70", newStudent(20), randomSectionID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 else {
			try {
				Section newSection = new Section(course, newProfessor(1).get(0), capacity, LocalTime.parse(randomClassTime()), "70", newStudent(20), randomSectionID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 }
	 
	 }
public static void progressStudent(Student s, ArrayList<Course> coursesCompleted) {
	//s.addCoursesCompleted(coursesCompleted);
	for(Course course : coursesCompleted) {
		s.addCoursesCompleted(course);
	}
}
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println();
		int i =0;
try
	{ 
		
		createMajors();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
		for(Major m: Major.getAllMajors()) {
			for(ArrayList<Course> cA : m.getPlan()) {
				for(Course c: cA) {
					createSections(c);
					i+=1;
					System.out.println(c.getName());
				}
			}
		}
		for(Section s: Section.getAllSections()) {
			
			for(Student stu: s.getStudentList()) {
				//System.out.println(stu.name + stu.id);
			}
		}
		

		for(Section s: Section.getAllSections()) {
			for(Student stu: s.getStudentList()) {
				progressStudent(stu, stu.currentCourses);
			}
		}
		
		

//		 Professor pickedProfessor = this.professors.get((new Random().nextInt(this.professors.size())));
//		 Section newSection = new Section(this, pickedProfessor, 20, this.duration, this.);
		


	}
}
