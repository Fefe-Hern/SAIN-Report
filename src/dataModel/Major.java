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

    /**
     *
     * @param codeName
     * @param name
     * @param GPAreq
     */
    public Major(String codeName, String name, double GPAreq) {
        this.codeName = codeName;
        this.name = name;
        this.GPAreq = GPAreq;
        electivesNeeded = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Elective> getElectivesNeeded() {
        ArrayList<Elective> copyOfElectivesNeeded = new ArrayList<>();
        for (int i = 0; i < electivesNeeded.size(); i++) {
            copyOfElectivesNeeded.add(electivesNeeded.get(i));
        }
        return copyOfElectivesNeeded;
    }
    
    /**
     *
     * @param electiveToAdd
     * @return
     */
    public boolean addElectiveToMajor(Elective electiveToAdd) {
        return electivesNeeded.add(electiveToAdd);
    }
    
    /**
     *
     * @param electiveToRemove
     * @return
     */
    public boolean removeElectiveFromMajor(Elective electiveToRemove) {
        setTotalCredits(getTotalCredits() - electiveToRemove.getCreditsRequired());
        for (Elective elective : electivesNeeded) {
            if (similar(elective.getName(), electiveToRemove.getName(),
                    elective.getCodeName(), electiveToRemove.getCodeName())) {
                setTotalCredits(totalCredits - elective.getCreditsRequired());
                return electivesNeeded.remove(elective);
            }
        }
        System.out.println("No elective deleted");
        return false;
    }

    /**
     *
     * @return
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public double getGPAreq() {
        return GPAreq;
    }

    /**
     *
     * @return
     */
    public int getTotalCredits() {
        return totalCredits;
    }

    /**
     *
     * @param GPAreq
     */
    public void setGPAreq(double GPAreq) {
        this.GPAreq = GPAreq;
    }

    /**
     *
     * @param totalCredits
     */
    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    private boolean similar(String name, String nameToRemove, String codeName, String codeNameToRemove) {
        return(name.equals(nameToRemove) && codeName.equals(codeNameToRemove));
    }

}
