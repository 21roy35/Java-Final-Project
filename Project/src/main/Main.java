package main;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		
		 Section section1 = new Section();

		System.out.println(section1);
		 Major computerEngineering = null;
		try {
			computerEngineering = new Major(null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(computerEngineering);
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
