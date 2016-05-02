package dataModel;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Classes implements Serializable {
    private String name;
    private String codeName;
    private int creditsGiven;
    private Instructor instructor;
    private HashMap<String, Student> studentMap;

    public Classes(String name, String codeName, int creditsGiven, Instructor instructor, int maxStudents) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        this.instructor = instructor;
        studentMap = new HashMap<>(maxStudents);
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
}
