package main;

import java.util.*;
import java.io.*;

public class Major {
    private String name;
    private ArrayList<ArrayList<Course>> plan = new ArrayList<>();
    private int credits;
    private static ArrayList<Major> allMajors = new ArrayList<>();

    public Major(File file) throws FileNotFoundException {
    	
        Scanner input = new Scanner(file);

        String[] tempArray = input.nextLine().split(",");
        if (tempArray[0].equals("name")) {
            this.name = tempArray[1].trim();
        }

        tempArray = input.nextLine().split(",");
        if (tempArray[0].equals("credits")) {
            try {
                this.credits = Integer.parseInt(tempArray[1].trim());
            } catch (NumberFormatException ex) {
                System.out.printf("Please check %s credits", this.name);
            }
        }

        for (int i = 0; i <= 11; i++) {
            plan.add(new ArrayList<>());
        }

        for (int i = 0; i <= 3; i++) {
            while (!input.hasNext("end")) {
                tempArray = input.nextLine().split(",");
                String tempName = tempArray[0].trim();
                System.out.println(tempName);
                int tempCredits = 0;
                try {
                    tempCredits = Integer.parseInt(tempArray[1].trim());
                } catch (NumberFormatException ex) {
                    System.out.printf("Please check %s credits", tempName);
                }
                ArrayList<Course> tempCourses = new ArrayList<>();
                for (int r = 2; r < tempArray.length - 1; r++) {
                    for (int s = 0; s <= 11; s++) {
                        for (int f = 0; f <= plan.get(s).size() - 1; f++) {
                            if (plan.get(s).get(f).getName().equals(tempArray[r]))
                                tempCourses.add(plan.get(s).get(f));
                        }
                    }
                }

                plan.get(i).add(new Course(tempName, tempCredits, tempCourses));
                tempCourses.clear();
                for (int k = 0; k < tempArray.length; k++) {
                    tempArray[k] = "";
                }
            }
            input.nextLine();
        }

        input.close();
        allMajors.add(this);
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
    public static ArrayList<Major> getAllMajors(){
    	return allMajors;
    }
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<ArrayList<Course>> createPlanForStudent() {

        return this.plan;
    }
}