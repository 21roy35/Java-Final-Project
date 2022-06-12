package main;

import java.util.ArrayList;

/**
 * Date: June 11-2022
 * This is the Elective class which extends Course and implements ElectiveInterface which shares everything with Course except that the credits are final
 * @author Team1
 *
 */
public class Elective extends Course implements ElectiveInterface{
    public Elective(String name, ArrayList<Course> prereqList) { //this subclass shares everything with Course except that the credits are final.
        super(name, credits, prereqList);
    }
}
