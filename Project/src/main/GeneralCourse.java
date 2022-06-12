package main;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Date: June 11-2022
 * This is the GeneralCourse class which extends Course and implements GeneralCourseInterface which shares everything with Course and has createSections and collegeRequirement extra methods
 * @author Team1
 *
 */
public class GeneralCourse extends Course implements GeneralCourseInterface{
	
    /**
     * this is a constructor that takes name, credit and prereqList as  an arguments
     * @param name initial name for GeneralCourse
     * @param credit initial credit for GeneralCourse
     * @param prereqList initial prereqLists for GeneralCourse
     */
    public GeneralCourse(String name, int credit, ArrayList<Course> prereqList) {
        super(name, credit, prereqList);
    }

    /**
     *this is an overridden method which will create new sections for the General Course and throw two exceptions if exists: FullSectionsException and NoAvailableProfessorException
     */
    @Override
    public void createSections() throws FullSectionsException, NoAvailableProfessorException {
        ArrayList<Course> term = Major.getTerm(this); //we want the term of the course, so we can avoid conflicts
        ArrayList<LocalTime> times = new ArrayList<>(); // the times of the sections of the courses in the same term

        for (int i = 0; i <= term.size() - 1; i++) { //in this loop, we collect the sections time to avoid them
            Course course = term.get(i); //get course
            ArrayList<LocalTime> sectionTime = course.getCourseSectionsTime(); //get the course sections time
            times.addAll(sectionTime); //add the sections time to the list
        }

        LocalTime sectionTime = Main.randomClassTime(); //create a random section time
        for (int i = 0; i <= times.size() - 1; i++) { //check whether the section time is appropriate by comparing with times list
            LocalTime time = times.get(i);
            if (time.equals(sectionTime)) {
                sectionTime = Main.randomClassTime();
            }
        }

        ArrayList<Student> studentsNeedThisCourse = getStudentsNeedThisCourse();
        ArrayList<Professor> professorTeachThisCourse = getProfessorsTeachThisCourse();

        //this two integers is for the sizes of the two lists (students, professors)
        int studentsListSize = studentsNeedThisCourse.size();
        int professorsListSize = professorTeachThisCourse.size();

        if (studentsListSize == 0) { //we check if there is any students who need this course
            //skip the creation of sections because no student reached the course yet
        }
        else if (professorsListSize == 0) { //we check if there is any available professors
            throw new NoAvailableProfessorException(this);
        }
        else {
            if (((studentsListSize + professorsListSize - 1) / professorsListSize) > 60) { //first, we check if the professors are not enough
                //if they are not enough, we will throw FullSectionsException and add the student who could not register there
                int index = professorsListSize * 60;
                ArrayList<Student> studentsCouldNotRegister = new ArrayList<>(studentsNeedThisCourse.subList(index, studentsListSize));
                studentsNeedThisCourse.removeAll(studentsCouldNotRegister);
                throw new FullSectionsException(this, studentsCouldNotRegister);
            }
            //else create sections for this class
            //here we loop for the professors to create section for each one
            for (int i = 0; i <= professorsListSize - 1; i++) {
                Professor prof = professorTeachThisCourse.get(i);
                ArrayList<Student> tempStudentList = new ArrayList<>();

                try {
                    ArrayList<Student> tempListForRegistration = new ArrayList<>(studentsNeedThisCourse.subList(60 * i, 60 * (i + 1)));
                    tempStudentList.addAll(tempListForRegistration);
                } catch (IndexOutOfBoundsException e) {
                    try {
                        ArrayList<Student> tempListForRegistration = new ArrayList<>(studentsNeedThisCourse.subList(60 * i, studentsListSize));
                        tempStudentList.addAll(tempListForRegistration);
                    } catch (IllegalArgumentException ex) {
                        break;
                    }
                }
                Department.createSectionForCourse(this, prof, 60, sectionTime, Main.ClassDuration(sectionTime), tempStudentList);
            }
        }
    }

    /**
     * this is an overridden method that will specify if the course is college requirement or not
     */
    @Override
    public boolean collegeRequirement() {
        boolean collegeRequirement;
        int courseCapacity = this.getSections().get(0).getCapacity();
        collegeRequirement = courseCapacity == 60;
        return collegeRequirement;
    }
}
