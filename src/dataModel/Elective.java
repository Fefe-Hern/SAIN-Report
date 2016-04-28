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

    public ArrayList<Course> getCoursesInElective() {
        ArrayList<Course> copyOfElectivesNeeded = new ArrayList<>();
        for (int i = 0; i < coursesInElective.size(); i++) {
            copyOfElectivesNeeded.add(coursesInElective.get(i));
        }
        return copyOfElectivesNeeded;
    }
    public boolean addCourseToMajor(Course courseToAdd) {
        return coursesInElective.add(courseToAdd);
    }
    
    public boolean removeElectiveFromMajor(Course courseToRemove) {
        return coursesInElective.remove(courseToRemove);
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
