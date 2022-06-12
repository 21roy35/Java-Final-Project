package main;

import java.util.ArrayList;

/**
 * Date: June 11-2022
 * This is the GeneralCourseInterface which forces the caller to override createSections and collegeRequirement methods
 * @author Team1
 *
 */
public interface GeneralCourseInterface {
    void createSections() throws Exception;
    boolean collegeRequirement();
}
