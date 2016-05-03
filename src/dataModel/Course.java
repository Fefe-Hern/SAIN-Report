package dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Course(String name, String codeName, int creditsGiven, HashMap<String, Classes> listOfClasses) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        this.listOfClasses = listOfClasses;
    }
    
    
    public Course deepCopy() {
        return new Course(name, codeName, creditsGiven,listOfClasses);
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
    
    public ArrayList<Classes> getClassesInCourse() {
        ArrayList<Classes> copyOfClassesInCourse = new ArrayList<>();
        for (Map.Entry<String, Classes> entry : listOfClasses.entrySet()) {
            copyOfClassesInCourse.add(entry.getValue());
        }
        return copyOfClassesInCourse;
    }
    
    public boolean addClass(Instructor instructor, String CRN) {
        try{
            Classes classToAdd = new Classes(getName(), getCodeName(), CRN, getCreditsGiven(), instructor);
            listOfClasses.put(CRN, classToAdd);
            instructor.addClassToTaught(classToAdd);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
