package Phase1;

import java.util.ArrayList;

public class Elective extends Course implements ElectiveInterface{
    public Elective(String name, ArrayList<Course> prereqList) {
        super(name, credits, prereqList);
    }
}
