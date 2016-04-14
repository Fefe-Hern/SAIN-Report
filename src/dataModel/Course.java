package dataModel;

import java.io.Serializable;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Course implements Serializable {
    private String name;
    private String codeName;
    private int creditsGiven;
    private final String CRN;

    public Course(String name, String codeName, int creditsGiven, String CRN) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        this.CRN = CRN;
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

    public String getCRN() {
        return CRN;
    }
    
    
    
    
}
