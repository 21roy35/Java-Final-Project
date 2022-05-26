package main;

import java.util.*;
import java.io.*;

public class Major {
    private String name;
    private ArrayList<ArrayList<Course>> plan = new ArrayList<>();
    private int credits;
    public static ArrayList<Major> allMajors = new ArrayList<>();

    public Major(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file); //Here we are reading the file

        String[] tempArray = input.nextLine().split(","); //The first part of the file is the name of the major, so read it
        if (tempArray[0].equals("name")) {
            this.name = tempArray[1].trim();
        } //Here we are checking whether the read line holds the name

        tempArray = input.nextLine().split(","); // Next we read the total number of credits for the major
        if (tempArray[0].equals("credits")) {
            try {
                this.credits = Integer.parseInt(tempArray[1].trim());
            } catch (NumberFormatException ex) {
                System.out.printf("Please check %s credits", this.name);
            } //since the number of credits is a string, we convert it to int using paeseint. Furthermore, we need to check for NumberFormatExpection
        }

        for (int i = 0; i <= 11; i++) {
            plan.add(new ArrayList<>());
        } //Here we initialize the semesters arraylists

        //Here is the most import part. We want to read the courses for each term. Therefore, we are using a for loop for the number of semesters.
        //Furthermore, there two semesters (iterations) for the electives and university courses.
        for (int i = 0; i <= 11; i++) {
            while (!input.hasNext("end")) {//this while is for seeing the end of an iteration
                if (input.hasNextLine()) {
                    tempArray = input.nextLine().split(","); //Here we split the line, which hold the info of the course
                    String tempName = tempArray[0].trim();//first part of the course is the name

                    int tempCredits = 0;
                    try {
                        tempCredits = Integer.parseInt(tempArray[1].trim());
                    } catch (NumberFormatException ex) {
                        System.out.printf("Please check %s credits", tempName);
                    } //The second part is the credits. Again, since it is a string we convert to an int while looking for the exception

                    ArrayList<Course> tempCourses = new ArrayList<>();
                    for (int r = 2; r <= tempArray.length - 1; r++) {
                        for (int s = 0; s <= Course.allCourses.size() - 1; s++) {
                            Course tempCourse = Course.allCourses.get(s);
                            if (tempCourse.getName().equals(tempArray[r].trim()))
                                tempCourses.add(tempCourse);
                        }
                    } //Last part of the course is the prerequisites. We check the prerequisite with its name in the allCourses list.
                    if (i == 0 | i == 1| i == 2| i == 3) {
                        plan.get(i).add(Department.createGeneralCourse(tempName, tempCredits, tempCourses)); // Then we create the course with createCourse method
                        tempCourses.clear();
                    }
                    else if (i == 10) {
                        plan.get(i).add(Department.createElective(tempName, tempCourses)); // Then we create the course with createCourse method
                        tempCourses.clear();
                    }
                    else if (i == 11) {
                        plan.get(i).add(Department.createUniversityCourse(tempName, tempCourses)); // Then we create the course with createCourse method
                        tempCourses.clear();
                    }
                    else {
                        plan.get(i).add(Department.createCourse(tempName, tempCredits, tempCourses)); // Then we create the course with createCourse method
                        tempCourses.clear();
                    }

                }
            }
            input.nextLine(); //we use this statement to read "end" which is in the end of the semester in file
        }

        allMajors.add(this); //We add the constructed object is added to the allMajors list
        input.close(); //We close the input stream
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Course>> getPlan() {
        return plan;
    }

    public void setPlan(ArrayList<ArrayList<Course>> plan) {
        this.plan = plan;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<ArrayList<Course>> createPlanForStudent() { //this method is for creating plans for students. The major object only holds a default plan
                                                                 //Therefore we need to create a specific plan for each student
        //The UniversityCourse will be in 9th semester
        //The Electives: one in 9th semester and two in 10th semester
        ArrayList<ArrayList<Course>> studentPlan = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            studentPlan.add(this.plan.get(i));
        }

        ArrayList<Course> tempCourseList = new ArrayList<>();

        int defaultPlanIndex = 10;
        int electiveIUpperBound = 7;
        int universityCourseUpperBound = 20;
        Random randomNum = new Random();

        tempCourseList.add(this.plan.get(defaultPlanIndex).get(randomNum.nextInt(universityCourseUpperBound)));
        tempCourseList.add(this.plan.get(defaultPlanIndex).get(randomNum.nextInt(electiveIUpperBound)));
        studentPlan.add(8, tempCourseList);
        tempCourseList.clear();

        tempCourseList.add(this.plan.get(defaultPlanIndex).get(randomNum.nextInt(electiveIUpperBound)));

        defaultPlanIndex = 11;

        tempCourseList.add(this.plan.get(defaultPlanIndex).get(randomNum.nextInt(electiveIUpperBound)));
        studentPlan.add(9, tempCourseList);

        System.out.println(studentPlan);
        return studentPlan;
    }

    public ArrayList<Major> searchForCourse(String name) {

        return new ArrayList<>();
    }

	public static ArrayList<Major> getAllMajors() {
		return allMajors;
	}
}