package main;

public class Main {

	public static void main(String[] args) {
		 Section section1 = new Section("section1");
		 Section section2 = new Section("section2");
		 Section section3 = new Section("section3");
		 Section section4 = new Section("section4");
		 Section section5 = new Section("section5");
		 Section section6 = new Section("section6");

		 Major computerEngineering = new Major();
		 
		 Professor hussain = new Professor("1","Hussain");
		 hussain.addCurrentSections(section1,section2);
		 System.out.println(hussain.currentSections);
		 hussain.addCurrentSections(section3,section4,section5,section6);
		 System.out.println(hussain.currentSections);
		 hussain.removeCurrentSections(section4,section3);
		 System.out.println(hussain.currentSections);
		 System.out.println(hussain.getLimit());
		 
		 Student ahmed = new Student("1","Ahmed", computerEngineering,);

	}
}
