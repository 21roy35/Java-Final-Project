package main;

import java.util.ArrayList;

public class UniversityCourse extends Course implements UniversityCourseInterface{ //this subclass shares everything with Course except that the credits are final.
    public UniversityCourse(String name, ArrayList<Course> prereqList) {
        super(name, credits, prereqList);
    }
}
