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

    public Classes(String name, String codeName, String crn, int creditsGiven, Instructor instructor) {
        this.name = name;
        this.codeName = codeName;
        this.crn = crn;
        this.creditsGiven = creditsGiven;
        this.instructor = instructor;
        studentList = new ArrayList<>();
        gpaList = new ArrayList<>();
    }

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

    public Classes deepCopy() {
        return new Classes(name, codeName, creditsGiven,crn, instructor, studentList, gpaList);
    }
    
    public Classes shallowCopy() {
        return this;
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

    public String getCrn() {
        return crn;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public ArrayList<Double> getGpaList() {
        return gpaList;
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

    public String getNameAndCode() {
        return codeName + "\t" + name;
    }
    
    public String getCodeAndGPA(String userName) {
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getUserName() == userName) {
                return String.valueOf(gpaList.get(i));
            }
        }
        return "GPA Not Found";
    }
    
}
