package dataModel;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Course implements Serializable {
    private String name;
    private String codeName;
    private int creditsGiven;
    private HashMap<String, Classes> listOfClasses;

    public Course(String name, String codeName, int creditsGiven) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        listOfClasses = new HashMap();
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
    
}
