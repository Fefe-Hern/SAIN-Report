package dataModel;

import java.util.ArrayList;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Major {
    private final String codeName;
    private String name;
    private double GPAreq;
    private int totalCredits;
    private ArrayList<Course> classesNeeded;

    public Major(String codeName, String name, double GPAreq, int totalCredits) {
        this.codeName = codeName;
        this.name = name;
        this.GPAreq = GPAreq;
        this.totalCredits = totalCredits;
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
