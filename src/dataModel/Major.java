package dataModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Major implements Serializable {
    private final String codeName;
    private String name;
    private double GPAreq;
    private int totalCredits;
    private ArrayList<Course> classesNeeded;
    private ArrayList<Course> cstCourses;
    private ArrayList<Course> engCourses;
    private ArrayList<Course> matCourses;
    private ArrayList<Course> labCourses;
    private ArrayList<Course> humCourses;
    private ArrayList<Course> hisCourses;
    private ArrayList<Course> pedCourses;
    private ArrayList<Course> socCourses;

    public Major(String codeName, String name, double GPAreq) {
        this.codeName = codeName;
        this.name = name;
        this.GPAreq = GPAreq;
        classesNeeded = new ArrayList<>();
    }
    
    public boolean addClassToMajor(Course classToAdd) {
        return classesNeeded.add(classToAdd);
    }
    
    public boolean removeClassFromMajor(Course classToRemove) {
        return classesNeeded.remove(classToRemove);
    }

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public double getGPAreq() {
        return GPAreq;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setGPAreq(double GPAreq) {
        this.GPAreq = GPAreq;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }
    
}
