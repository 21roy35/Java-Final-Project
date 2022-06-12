package main;

import java.util.*;
import java.io.*;
/**
 * Date: June 12-2022
 * This is the Major class
 * @author Team1
 *
 */
public class Major {
    /**
     * this is name of the major
     */
    private String name;
    /**
     * this is list for the plan
     */
    private ArrayList<ArrayList<Course>> plan = new ArrayList<>();
    /**
     * this is credits
     */
    private int credits;
    /**
     * this is major symbol
     */
    private String sym;
    /**
     * this is the list for major
      */
    public static ArrayList<Major> allMajors = new ArrayList<>();

    /**
     * this is the constructor thats initiat file
     * @param file assign value to the file
     * @throws FileNotFoundException
     */
    public Major(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file); //Here we are reading the file
        String[] tempArray; //we only create array, so we can split lines in the file

        tempArray = input.nextLine().split(","); //The first part of the file is the name of the major, so read it
        if (tempArray[0].equals("name")) { //Here we are checking whether the read line holds the name
            this.name = tempArray[1].trim();
        }

        tempArray = input.nextLine().split(","); // Next we read the symbol for the major
        if (tempArray[0].equals("sym")) { //Here we are checking whether the read line holds the symbol
            this.sym = tempArray[1].trim();
        }

        tempArray = input.nextLine().split(","); // Next we read the total number of credits for the major
        if (tempArray[0].equals("credits")) { //Here we are checking whether the read line holds the credits
            try { //since the number of credits is a string, we convert it to int using parseint
                this.credits = Integer.parseInt(tempArray[1].trim());
            } catch (NumberFormatException ex) { //furthermore, we need to check for NumberFormatException
                System.out.printf("Please check %s credits", this.name);
            }
        }

        for (int i = 0; i <= 11; i++) { //Here we initialize the semesters arraylists
            plan.add(new ArrayList<>());
        }

        //Here is the most import part. We want to read the courses for each term. Therefore, we are using a for loop for the number of semesters.
        //Furthermore, the last two semesters (iterations) are for the electives and university courses.
        for (int i = 0; i <= 11; i++) {
            while (!input.hasNext("end")) {//this while checks if the semester (iteration) has ended
                if (input.hasNextLine()) { //here check if a line exists
                    tempArray = input.nextLine().split(","); //Here we split the line, which hold the info of the course

                    //first part of the course is the name
                    String tempName = tempArray[0].trim();

                    //The second part is the credits. Again, since it is a string we convert to an int while looking for the exception
                    int tempCredits = 0;
                    try {
                        tempCredits = Integer.parseInt(tempArray[1].trim());
                    } catch (NumberFormatException ex) {
                        System.out.printf("Please check %s credits", tempName);
                    }

                    //Last part of the course is the prerequisites. We check the prerequisite with its name in the allCourses list.
                    ArrayList<Course> tempCourses = new ArrayList<>(); //we initialize an arraylist to hold the prerequisite
                    for (int r = 2; r <= tempArray.length - 1; r++) { //then we loop the prerequisites in the file
                        for (int s = 0; s <= Course.allCourses.size() - 1; s++) { //the prerequisites should be all already created, so we only search for them
                            Course tempCourse = Course.allCourses.get(s);
                            String courseName = tempCourse.getName();
                            String prerequisiteName = tempArray[r].trim();

                            if (courseName.equals(prerequisiteName)) {
                                tempCourses.add(tempCourse);
                            }
                        }
                    }

                    ArrayList<Course> semester = plan.get(i);
                    switch (i) { //based on the index of the semester in the file we create different courses
                        //from iterations 0 to 3, they are generalCourses
                        case 0, 1, 2, 3 -> {
                            //create the general course with createGeneralCourse method
                            Course course = Department.createGeneralCourse(tempName, tempCredits, tempCourses);
                            semester.add(course);
                        }
                        //in iteration 10, they are electives
                        case 10 -> {
                            //create the elective with createElective method
                            Course course = Department.createElective(tempName, tempCourses);
                            semester.add(course);
                        }
                        //in iteration 11, they are universityCourses
                        case 11 -> {
                            //create the university course with createUniversityCourse method
                            Course course = Department.createUniversityCourse(tempName, tempCourses);
                            semester.add(course);
                        }
                        //else, they are normal courses
                        default -> {
                            Course course = Department.createCourse(tempName, tempCredits, tempCourses);
                            semester.add(course); //create the course with createCourse method
                        }
                    }
                    tempCourses.clear();
                }
            }
            input.nextLine(); //we use this statement to read "end" which is in the end of the semester in the file
        }

        this.credits = checkCredits(); //here we check the credits of all the courses
        allMajors.add(this); //We add the constructed object to the allMajors list
        input.close(); //We close the input stream
    }

    /**
     * this is major constructor thats initiate major's  " name , symbol "
      * @param name assign value to the major name
     * @param sym  assign value to major symbol
     */
    public Major(String name, String sym) {
        this.name = name;
        this.sym = sym;
    }

    /**
     * this is method to set major name
     * @param name assign value to the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * this is a method to get the major's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * this is a method to set the plan list
     * @param plan assign value to the plan
     */


    public void setPlan(ArrayList<ArrayList<Course>> plan) {
        this.plan = plan;
    }

    /**
     * this the a method to get the plan list
     * @return plan
     */
    public ArrayList<ArrayList<Course>> getPlan() {
        return plan;
    }

    /**
     * this is a method to set
     * @param credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * this is a method that get credits
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * this is a method to set symbol for major
     * @param sym assign value to the sym
     */
    public void setSym(String sym) {
        this.sym = sym;
    }

    /**
     * this is a method to get symbol
     * @return symbol
     */
    public String getSym() {
		return this.sym;
	}

    /**
     * this is a method to get list of all majors
     * @return allMajors
     */
    public static ArrayList<Major> getAllMajors() {
        return allMajors;
    }

    /**
     *  this is a method thats create plans for students
     * @return student plan
     */

    public ArrayList<ArrayList<Course>> createPlanForStudent() {
        //this method is for creating plans for students. The major object only holds a default plan
        //Therefore we need to create a specific plan for each student
        //The first four semesters will be changed randomly because of plan A and B for the foundation year and the new ENG student
        //The UniversityCourse will be in 9th semester
        //The Electives: one in 9th semester and two in 10th semester
        ArrayList<ArrayList<Course>> studentPlan = new ArrayList<>();
        Random randomNum = new Random();

        int planAorB = randomNum.nextInt(2);

        if (planAorB == 0) {
            for (int i = 0; i <= 9; i++) {
                ArrayList<Course> tempSemester = this.plan.get(i);
                studentPlan.add(tempSemester);
            }
        }
        else {
            studentPlan.add(this.plan.get(1));
            studentPlan.add(this.plan.get(0));
            studentPlan.add(this.plan.get(3));
            studentPlan.add(this.plan.get(2));
            
            for (int i = 4; i <= 9; i++) {
                ArrayList<Course> tempSemester = this.plan.get(i);
                studentPlan.add(tempSemester);
            }
        }

        ArrayList<Course> tempCourseList = new ArrayList<>();
        ArrayList<Course> electiveList = this.plan.get(10);
        ArrayList<Course> universityCourseList = this.plan.get(11);

        int electiveIUpperBound = electiveList.size();
        int universityCourseUpperBound = universityCourseList.size();


        int randomUniversityCourse = randomNum.nextInt(universityCourseUpperBound);
        int randomElective1 = randomNum.nextInt(electiveIUpperBound);
        int randomElective2 = randomNum.nextInt(electiveIUpperBound);
        int randomElective3 = randomNum.nextInt(electiveIUpperBound);

        Course tempUniversityCourse = universityCourseList.get(randomUniversityCourse);
        Course tempElective1 = electiveList.get(randomElective1);
        Course tempElective2 = electiveList.get(randomElective2);
        Course tempElective3 = electiveList.get(randomElective3);

        tempCourseList.add(tempUniversityCourse);
        tempCourseList.add(tempElective1);
        studentPlan.get(8).addAll(tempCourseList);
        tempCourseList.clear();

        tempCourseList.add(tempElective2);
        tempCourseList.add(tempElective3);
        studentPlan.get(9).addAll(tempCourseList);
        tempCourseList.clear();

        return studentPlan;
    }

    /**
     * this is a method thats check for credits
     * @return trueCredits
     */
    public int checkCredits() { //here we want to check the credits of a major from its plan
        int trueCredits = 10; //we first initialize 10 which corresponds to the electives and universityCourse
        for (int i = 0; i <= 9; i++) { //we loop the 10 semesters (iterations) from the plan
            ArrayList<Course> semester = this.plan.get(i);
            for (int r = 0; r <= semester.size() - 1; r++) { //in this loop we take all the courses credits and add them to trueCredits
                Course course = semester.get(r);
                int courseCredits = course.getCredits();
                trueCredits += courseCredits;
            }
        }
        return trueCredits;
    }

    /**
     * this is a method to get semster
     * @param course assign value to the course
     * @return term
     */
    public static ArrayList<Course> getTerm(Course course) { //we use this method to find the term where the course is taken
        try {
            ArrayList<Course> term = new ArrayList<>(); //initialize arraylist for term
            for (int i = 0; i <= allMajors.size() - 1; i++) { //this loop is checking for the major's plan
                Major major = allMajors.get(i);
                for (int r = 0; r <= 9; r++) { //this loop is checking for the plan's semesters
                    ArrayList<Course> tempTerm = major.getPlan().get(r);
                    for (int s = 0; s <= tempTerm.size(); s++) { //finally, this loop is checking for the course
                        Course tempCourse = tempTerm.get(s);
                        if (tempCourse == course) {
                            tempTerm.remove(course);
                            term.addAll(tempTerm);
                        }
                    }
                }
            }
            return term;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return new ArrayList<>();
        }
    }

	@Override
	public String toString() {
		return name;
	}
}