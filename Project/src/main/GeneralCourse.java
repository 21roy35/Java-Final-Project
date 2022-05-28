package main;

import java.util.ArrayList;

public class GeneralCourse extends Course implements GeneralCourseInterface{
    public GeneralCourse(String name, int credit, ArrayList<Course> prereqList) {
        super(name, credit, prereqList);
    }

    @Override
    public ArrayList<Section> createSections() {
        return null;
    }

    @Override
    public boolean collegeRequirement() {

        return false;
    }
}
