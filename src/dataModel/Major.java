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
    private ArrayList<Elective> electivesNeeded;

    public Major(String codeName, String name, double GPAreq) {
        this.codeName = codeName;
        this.name = name;
        this.GPAreq = GPAreq;
        electivesNeeded = new ArrayList<>();
    }
    
    public ArrayList<Elective> getElectivesNeeded() {
        ArrayList<Elective> copyOfElectivesNeeded = new ArrayList<>();
        for (int i = 0; i < electivesNeeded.size(); i++) {
            copyOfElectivesNeeded.add(electivesNeeded.get(i));
        }
        return copyOfElectivesNeeded;
    }
    
    public boolean addElectiveToMajor(Elective electiveToAdd) {
        return electivesNeeded.add(electiveToAdd);
    }
    
    public boolean removeElectiveFromMajor(Elective electiveToRemove) {
        return electivesNeeded.remove(electiveToRemove);
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
