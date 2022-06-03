package main;

import java.util.ArrayList;

public class Elective extends Course implements ElectiveInterface{
    public Elective(String name, ArrayList<Course> prereqList) { //this subclass shares everything with Course except that the credits are final.
        super(name, credits, prereqList);
    }
}
