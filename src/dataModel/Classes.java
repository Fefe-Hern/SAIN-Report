package dataModel;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Classes implements Serializable {
    private String name; //Given by Course
    private String codeName; // Given by Course
    private int creditsGiven; // Given by Course
    private String crn;
    private Instructor instructor;
    private HashMap<String, Student> studentMap;

    public Classes(String name, String codeName, String crn, int creditsGiven, Instructor instructor) {
        this.name = name;
        this.codeName = codeName;
        this.crn = crn;
        this.creditsGiven = creditsGiven;
        this.instructor = instructor;
        studentMap = new HashMap<>();
    }

    public Classes(String name, String codeName, int creditsGiven, Instructor instructor, HashMap<String, Student> studentMap) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        this.instructor = instructor;
        this.studentMap = studentMap;
    }
    
    public Classes deepCopy() {
        return new Classes(name, codeName, creditsGiven, instructor, studentMap);
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }

    public int getCreditsGiven() {
        return creditsGiven;
    }

    public void setCreditsGiven(int creditsGiven) {
        this.creditsGiven = creditsGiven;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    
    public String getCRNAndInstructor() {
        return crn + "\t" + instructor.getLastName();
    }
    
    public String getCRNAndName() {
        return crn + "\t" + name;
    }
}
