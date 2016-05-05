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

    public Student(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        classesCompleted = new ArrayList<>();
        currentClasses = new ArrayList<>();
        classesTaken = new ArrayList<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
        
    }
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Major getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public ArrayList<Classes> getClassesCompleted() {
        return classesCompleted;
    }

    public ArrayList<Classes> getCurrentClasses() {
        return currentClasses;
    }

    public ArrayList<Classes> getClassesTaken() {
        return classesTaken;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean addClassToCurrentClasses(Classes classToAdd) {
        if(currentClasses.contains(classToAdd)) {
            return false;
        } else {
            currentClasses.add(classToAdd);
            return true;
        }
    }

}
