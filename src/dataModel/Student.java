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
    private String id;
    
    private Major major;
    private double gpa;
    private ArrayList<Course> classesCompleted;
    private ArrayList<Course> currentClasses;
    private ArrayList<Course> classesTaken;

    public Student(String userName, String password, String firstName, String lastName, String id) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
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
    public String getId() {
        return id;
    }

    public Major getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public ArrayList<Course> getClassesCompleted() {
        return classesCompleted;
    }

    public ArrayList<Course> getCurrentClasses() {
        return currentClasses;
    }

    public ArrayList<Course> getClassesTaken() {
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

}
