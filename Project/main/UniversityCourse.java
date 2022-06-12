package main;

import java.util.ArrayList;
/**
 * Date: June 11-2022
 * This is the Elective class which extends Course and implements UniversityCourseInterface which shares everything with Course except that the credits are final
 * @author Team1
 *
 */
public class UniversityCourse extends Course implements UniversityCourseInterface{ //this subclass shares everything with Course except that the credits are final.
    public UniversityCourse(String name, ArrayList<Course> prereqList) {
        super(name, credits, prereqList);
    }
}
