package main;

import java.util.ArrayList;

public class UniversityCourse extends Course implements UniversityCourseInterface{
    public UniversityCourse(String name, ArrayList<Course> prereqList) {
        super(name, credits, prereqList);
    }
}
