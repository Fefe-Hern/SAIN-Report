package dataModel;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<Student> studentList;
    private ArrayList<Double> gpaList;

    /**
     *
     * @param name
     * @param codeName
     * @param crn
     * @param creditsGiven
     * @param instructor
     */
    public Classes(String name, String codeName, String crn, int creditsGiven, Instructor instructor) {
        this.name = name;
        this.codeName = codeName;
        this.crn = crn;
        this.creditsGiven = creditsGiven;
        this.instructor = instructor;
        studentList = new ArrayList<>();
        gpaList = new ArrayList<>();
    }

    /**
     *
     * @param name
     * @param codeName
     * @param creditsGiven
     * @param crn
     * @param instructor
     * @param studentList
     * @param gpaList
     */
    public Classes(String name, String codeName, int creditsGiven, String crn, Instructor instructor,
            ArrayList<Student> studentList, ArrayList<Double> gpaList) {
        this.name = name;
        this.codeName = codeName;
        this.creditsGiven = creditsGiven;
        this.crn = crn;
        this.instructor = instructor;
        this.studentList = studentList;
        this.gpaList = gpaList;
    }

    /**
     *
     * @return
     */
    public Classes deepCopy() {
        return new Classes(name, codeName, creditsGiven,crn, instructor, studentList, gpaList);
    }
    
    /**
     *
     * @return
     */
    public Classes shallowCopy() {
        return this;
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
    public String getCodeName() {
        return codeName;
    }

    /**
     *
     * @return
     */
    public int getCreditsGiven() {
        return creditsGiven;
    }

    /**
     *
     * @param creditsGiven
     */
    public void setCreditsGiven(int creditsGiven) {
        this.creditsGiven = creditsGiven;
    }

    /**
     *
     * @return
     */
    public String getCrn() {
        return crn;
    }

    /**
     *
     * @return
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Double> getGpaList() {
        return gpaList;
    }
    
    /**
     *
     * @return
     */
    public Instructor getInstructor() {
        return instructor;
    }
    
    /**
     *
     * @return
     */
    public String getCRNAndInstructor() {
        return crn + "\t" + instructor.getLastName();
    }
    
    /**
     *
     * @return
     */
    public String getCRNAndName() {
        return crn + "\t" + name;
    }

    /**
     *
     * @return
     */
    public String getNameAndCode() {
        return codeName + "\t" + name;
    }
    
    /**
     *
     * @param userName
     * @return
     */
    public String getCodeAndGPA(String userName) {
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getUserName() == userName) {
                return String.valueOf(gpaList.get(i));
            }
        }
        return "GPA Not Found";
    }
    
}
