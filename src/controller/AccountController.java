package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import dataModel.Accounts;
import dataModel.Admin;
import dataModel.Classes;
import dataModel.Instructor;
import dataModel.Student;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import window.instructor.ViewClassesTaughtWindow;
import window.sainReport.SainReportWindow;

/**
 * AccountController keeps track of the accounts in the file.
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AccountController {
    private static HashMap<String, Accounts> accountMap;
    private static File file;

    /**
     * Called to load up AccountInfo or create a new file named AccountInfo.
     */
    public static void initialize() {
        file = new File("AccountInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                accountMap = (HashMap<String, Accounts>) ois.readObject();
                ois.close();
                System.out.println("Accounts loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Accounts file created.");
                    accountMap = initializeAccountMap();
                    save();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void save() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accountMap);
            oos.close();
            System.out.println("Accounts saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HashMap<String, Accounts> initializeAccountMap() {
        HashMap<String, Accounts> map = new HashMap<>();
        map.put("hernf07", new Admin("hernf07", "test", "Fernando", "Hernandez"));
        map.put("hernf08", new Instructor("hernf08", "test", "Fefe", "Hern"));
        map.put("hernf09", new Student("hernf09", "test", "Fefef", "Hernh"));
        return map;
    }
    
    /**
     * Obtains the account associated with the username and password.
     * @param userName username of account
     * @param password inputted password
     * @return The account if password is correct, null otherwise.
     */
    public static Accounts getAccount(String userName, String password) {
        if(accountMap.containsKey(userName)) {
            if(password.equals(accountMap.get(userName).getPassword())){
                return accountMap.get(userName);
            }
        }
        return null;
    }
    
    /**
     * Adds a new account to the list of accounts
     * @param newAccount
     * @return true if the username does not already exist
     */
    public static boolean addAccount(Accounts newAccount) {
        if(!accountMap.containsKey(newAccount.getUserName())) {
            accountMap.put(newAccount.getUserName(), newAccount);
            save();
            return true;
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Taken Username Error");
        alert.setHeaderText("Error:");
        alert.setContentText("The username " + newAccount.getUserName() + " has been taken");
        alert.showAndWait();
        return false;
    }

    /**
     * Saves the account properly to the list of accounts in the proper format, be it Student Instructor or Admin.
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param type
     * @return
     */
    public static boolean saveAccount(String firstName, String lastName, String userName,
            String password, String type) {
        userName = userName.toLowerCase();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
            switch (type) {
                case "Student":
                    return addAccount(new Student(userName, password, firstName, lastName));
                case "Instructor":
                    return addAccount(new Instructor(userName, password, firstName, lastName));
                case "Admin":
                    return addAccount(new Admin(userName, password, firstName, lastName));
            }
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Empty Textfield Error");
        alert.setHeaderText("Error:");
        alert.setContentText("One or more of the textfields were empty.");
        alert.showAndWait();
        
        return false;
    }

    /**
     * Finds and checks if the instructor exists.
     * @param instructorId
     * @return
     */
    public static boolean searchForInstructor(String instructorId) {
        Accounts member = accountMap.get(instructorId);
        try {
            if(member != null && member instanceof Instructor) {
                return true;
            }
        } catch(Exception e) {
            System.out.println("Error found whilst looking for instructor");
        }
        return false;
    }
    
    /**
     * Passes the instructor's information to the view
     * @param instructorId
     * @return the Instructor obtained.
     */
    public static Instructor passInstructor(String instructorId) {
        Accounts member = accountMap.get(instructorId);
        return (Instructor) member;
    }
    
    /**
     * Passes the Student's information to the view.
     * @param studentUserName
     * @return the Student obtained.
     */
    public static Student passStudent(String studentUserName) {
        Accounts member = accountMap.get(studentUserName);
        return (Student) member;
    }
    
    /**
     * Returns the student to the view through finding the correct full name.
     * @param studentFullName
     * @return the correct Student.
     */
    public static Student passStudentByFullName(String studentFullName) {
        ArrayList<Accounts> accountList = new ArrayList<>();
        for (Map.Entry<String, Accounts> entry : accountMap.entrySet()) {
            accountList.add(entry.getValue());
        }
        
        int iterator = 0;
        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getFullName().equals(studentFullName)) {
                iterator = i;
            }
        }
        return (Student) accountList.get(iterator);
    }

    /**
     * Loads up the classes taught by the instructor to be used to input to the view.
     * @param instructorUserName
     */
    public static void loadClassesForInstructor(String instructorUserName) {
        Instructor instructor = (Instructor) accountMap.get(instructorUserName);
        ArrayList<Classes> classList = instructor.getClassesTaught();
        for (int i = 0; i < classList.size(); i++) {
            ViewClassesTaughtWindow.addClassToData(classList.get(i).getCRNAndName());
        }
    }

    /**
     * Sets the Student to the major specified.
     * @param accountUserName
     * @param selectedMajor
     */
    public static void setMajorForStudent(String accountUserName, String selectedMajor) {
        Student student = (Student) accountMap.get(accountUserName);
        student.setMajor(MajorController.getMajor(selectedMajor));
    }

    /**
     * Assigns the class specified to the student.
     * @param userAccountName
     * @param crn The class CRN code.
     * @return true if the class exists and the student isn't already part of the class.
     */
    public static boolean addClassToStudent(String userAccountName, String crn) {
        if(ClassController.passClassToView(crn) == null) {
            return false;
        }
        Classes classToAdd = ClassController.passClassToView(crn);
        Student studentAccount = AccountController.passStudent(userAccountName);
            Alert alert = new Alert(AlertType.ERROR);
        if (classToAdd == null) {
            alert.setTitle("No CRN Found");
            alert.setHeaderText("Error:");
            alert.setContentText("CRN is not available.");
            alert.showAndWait();
            return false;
        }
        if(studentAccount.addClassToCurrentClasses(classToAdd)) {
            ClassController.addStudentToClass(studentAccount, crn);
            return true;
        } else {
            alert.setTitle("Class already taken");
            alert.setHeaderText("Error:");
            alert.setContentText("You are already taking this class!");
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Loads up the classes that have been completed by the student.
     * @param userAccountName
     */
    public static void loadClassesTaken(String userAccountName) {
        ArrayList<Classes> classesTaken = passStudent(userAccountName).getClassesCompleted();
        for (Classes classes : classesTaken) {
            SainReportWindow.addClassToClassesTaken(classes.getNameAndCode());
        }
    }

    /**
     * Loads the classes that the student is currently taking.
     * @param userAccountName
     */
    public static void loadClassesCurrentlyTaking(String userAccountName) {
        ArrayList<Classes> classesCurrentlyTaking = passStudent(userAccountName).getCurrentClasses();
        for (Classes classes : classesCurrentlyTaking) {
            SainReportWindow.addClassToCurrentlyTaking(classes.getNameAndCode());
        }
    }

    
    static void finishClass(Student student, Classes classToClear) {
        int iterator = 0;
        Student studentToFinish = (Student) accountMap.get(student.getUserName());
        for (int i = 0; i < student.getCurrentClasses().size(); i++) {
            if(classToClear.getCrn().equals(student.getCurrentClasses().get(i).getCrn())) {
                iterator = i;
            }
        }
        studentToFinish.getCurrentClasses().remove(iterator);
        studentToFinish.getClassesCompleted().add(classToClear);
    }

    static void removeInstructorTeachingClass(Instructor instructor, String crn) {
        Instructor instructorToFinish = (Instructor) accountMap.get(instructor.getUserName());
        int iterator = 0;
        for (int i = 0; i < instructorToFinish.getClassesTaught().size(); i++) {
            if(instructorToFinish.getClassesTaught().get(i).getCrn().equals(crn)) {
                iterator = i;
            }
        }
        instructorToFinish.getClassesTaught().remove(iterator);
    }
}