package main;

import java.util.*;
import java.io.*;

public class Major {
    private String name;
    private ArrayList<ArrayList<Course>> plan = new ArrayList<>();
    private int credits;
    public static ArrayList<Major> allMajors = new ArrayList<>();
    private String sym; //this should be in department and not major

    public Major(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file); //Here we are reading the file

        String[] tempArray = input.nextLine().split(","); //The first part of the file is the name of the major, so read it
        if (tempArray[0].equals("name")) {
            this.name = tempArray[1].trim();
        } //Here we are checking whether the read line holds the name

        tempArray = input.nextLine().split(","); // Next we read the total number of credits for the major
        if (tempArray[0].equals("sym")) {
            try {
                this.sym = tempArray[1].trim();
            } catch (NumberFormatException ex) {
                System.out.printf("Please check %s credits", this.name);
            } //since the number of credits is a string, we convert it to int using paeseint. Furthermore, we need to check for NumberFormatExpection
        }
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

                    switch (i) { //based on the index of the semester in the file we create different courses
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            plan.get(i).add(Department.createGeneralCourse(tempName, tempCredits, tempCourses)); // Then we create the course with createCourse method
                            tempCourses.clear();
                            break;
                        case 10:
                            plan.get(i).add(Department.createElective(tempName, tempCourses)); // Then we create the course with createCourse method
                            tempCourses.clear();
                            break;
                        case 11:
                            plan.get(i).add(Department.createUniversityCourse(tempName, tempCourses)); // Then we create the course with createCourse method
                            tempCourses.clear();
                            break;
                        default:
                            plan.get(i).add(Department.createCourse(tempName, tempCredits, tempCourses)); // Then we create the course with createCourse method
                            tempCourses.clear();
                    }
                }
            }
            input.nextLine(); //we use this statement to read "end" which is in the end of the semester in file
        }
        this.credits = checkCredits(); //here we check the credits
        allMajors.add(this); //We add the constructed object is added to the allMajors list
        input.close(); //We close the input stream
    }

    public Major(String name, String Sym) {
        this.name = name;
        this.sym = Sym;
        for (int i = 0; i <= 11; i++) {
            ArrayList<Course> tempSemester = new ArrayList<>();
            this.plan.add(tempSemester);
        }
    }

    public String getSym() {
		return this.sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
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
        //The first four semesters will be changed randomly because of plan A and B for the foundation year and the new ENG student
        //The UniversityCourse will be in 9th semester
        //The Electives: one in 9th semester and two in 10th semester
        ArrayList<ArrayList<Course>> studentPlan = new ArrayList<>();
        Random randomNum = new Random();

        int planAorB = randomNum.nextInt(1);

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

        int electiveIUpperBound = this.plan.get(10).size() - 1;
        int universityCourseUpperBound = this.plan.get(11).size() - 1;


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
        studentPlan.add(8, tempCourseList);
        tempCourseList.clear();

        tempCourseList.add(tempElective2);
        tempCourseList.add(tempElective3);
        studentPlan.add(9, tempCourseList);
        tempCourseList.clear();

        return studentPlan;
    }

	public static ArrayList<Major> getAllMajors() {
		return allMajors;
	}

    public int checkCredits() {
        int trueCredits = 10;
        for (int i = 0; i <= 9; i++) {
            ArrayList<Course> semester = this.plan.get(i);
            for (int r = 0; r <= semester.size() - 1; r++) {
                trueCredits += semester.get(r).getCredits();
            }
        }
        return trueCredits;
    }

    public static ArrayList<Course> getTerm(Department department, Course course) {
        try {
            ArrayList<Major> majors = department.getMajors();
            ArrayList<Course> term = new ArrayList<>();
            for (int i = 0; i <= majors.size() - 1; i++) {
                Major major = majors.get(i);
                for (int r = 0; r <= 9; r++) {
                    ArrayList<Course> tempTerm = major.getPlan().get(r);
                    for (int s = 0; s <= tempTerm.size(); s++) {
                        Course tempCourse = tempTerm.get(s);
                        if (tempCourse == course) {
                            term = tempTerm;
                        }
                    }
                }
            }
            return term;
        } catch (NullPointerException e) {
            return new ArrayList<>();
        } catch (IndexOutOfBoundsException x) {
            return new ArrayList<>();
        }
    }

	@Override
	public String toString() {
		return name;
	}
    
}