package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {

	//lol
	public static String[] RandomNames(int number) {
	    String[] first = {"Mohammed", "Ahmed", "Ali", "Sami", "Omar", "Samer", "Nour", "Tamer", "Lina", "Dina", "Nada", "Hana", "Rania", "Yasmine", "Lamia", "Salma", "Aya", "Heba", "Mona", "Reem"};
	    String[] last = {"Smith", "Jones", "Brown", "Williams", "Wilson", "Taylor", "Davies", "Evans", "Thomas", "Roberts", "Hughes", "Turner", "Price", "Morgan", "Bell", "Cooper", "Richardson", "Murphy", "Bailey", "Collins"};
	    Random rand = new Random();
	    String[] result = new String[number];
	    for (int i = 0; i < number; i++) {
	        result[i] = first[rand.nextInt(number)] + " " + last[rand.nextInt(number)];
	    }
	    return result;
	}
	public static void main(String[] args) throws FileNotFoundException {
		
		
//		 Professor pickedProfessor = this.professors.get((new Random().nextInt(this.professors.size())));
//		 Section newSection = new Section(this, pickedProfessor, 20, this.duration, this.);
		

		 Section section1 = new Section();
		 section1.addStudents();
		System.out.println(section1);
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
