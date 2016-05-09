package dataModel;

import java.util.ArrayList;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Student implements Accounts {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    
    private Major major;
    private double gpa;
    private ArrayList<Classes> classesCompleted;
    private ArrayList<Classes> currentClasses;
    private ArrayList<Classes> classesTaken;

    /**
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     */
    public Student(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        classesCompleted = new ArrayList<>();
        currentClasses = new ArrayList<>();
        classesTaken = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFirstName() {
        return firstName;
        
    }

    /**
     *
     * @return
     */
    @Override
    public String getLastName() {
        return lastName;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     *
     * @return
     */
    public Major getMajor() {
        return major;
    }

    /**
     *
     * @return
     */
    public double getGpa() {
        return gpa;
    }

    /**
     *
     * @return
     */
    public ArrayList<Classes> getClassesCompleted() {
        return classesCompleted;
    }

    /**
     *
     * @return
     */
    public ArrayList<Classes> getCurrentClasses() {
        return currentClasses;
    }

    /**
     *
     * @return
     */
    public ArrayList<Classes> getClassesTaken() {
        return classesTaken;
    }

    /**
     *
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param firstName
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param major
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     *
     * @param gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     *
     * @param classToAdd
     * @return
     */
    public boolean addClassToCurrentClasses(Classes classToAdd) {
        for (Classes classes : currentClasses) {
            if(classToAdd.getCrn().equals(classes.getCrn())) {
                return false;
            }
        }
        if(currentClasses.contains(classToAdd)) {
            return false;
        } else {
            currentClasses.add(classToAdd);
            return true;
        }
    }

}
