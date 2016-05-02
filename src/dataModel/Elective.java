package dataModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Elective implements Serializable {
    private String name;
    private String codeName;
    private int creditsRequired;
    private int totalCreditsOfClasses;
    private ArrayList<Course> coursesInElective;

    public Elective(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
        coursesInElective = new ArrayList<>();
    }

    public Elective(String name, String codeName, int creditsRequired, int totalCreditsOfClasses, ArrayList<Course> coursesInElective) {
        this.name = name;
        this.codeName = codeName;
        this.creditsRequired = creditsRequired;
        this.totalCreditsOfClasses = totalCreditsOfClasses;
        this.coursesInElective = coursesInElective;
    }

    public ArrayList<Course> getCoursesInElective() {
        ArrayList<Course> copyOfElectivesNeeded = new ArrayList<>();
        for (int i = 0; i < coursesInElective.size(); i++) {
            copyOfElectivesNeeded.add(coursesInElective.get(i));
        }
        return copyOfElectivesNeeded;
    }
    public boolean addCourseToElective(Course courseToAdd) {
        return coursesInElective.add(courseToAdd);
    }
    
    public boolean removeCourseFromElective(Course courseToRemove) {
        return coursesInElective.remove(courseToRemove);
    }
    
    public Elective deepCopy() {
        ArrayList<Course> coursesInElectiveDeepCopy = new ArrayList();
        for (Course course : coursesInElective) {
            coursesInElectiveDeepCopy.add(course);
        }
        return new Elective(name, codeName, creditsRequired, totalCreditsOfClasses, coursesInElectiveDeepCopy);
    }
    
    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }

    public int getCreditsRequired() {
        return creditsRequired;
    }

    public void setCreditsRequired(int creditsRequired) {
        this.creditsRequired = creditsRequired;
    }

    public int getTotalCreditsOfClasses() {
        return totalCreditsOfClasses;
    }

    public void setTotalCreditsOfClasses(int totalCreditsOfClasses) {
        this.totalCreditsOfClasses = totalCreditsOfClasses;
    }
    
    
}
