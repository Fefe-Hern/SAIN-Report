package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import dataModel.Accounts;
import dataModel.Admin;
import dataModel.Classes;
import dataModel.Course;
import dataModel.Instructor;
import dataModel.Student;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import window.instructor.ViewClassesTaughtWindow;
import window.sainReport.SainReportWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AccountSaver {
    private static HashMap<String, Accounts> accountMap;
    private static File file;
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
    
    public static Accounts getAccount(String userName, String password) {
        if(accountMap.containsKey(userName)) {
            if(password.equals(accountMap.get(userName).getPassword())){
                return accountMap.get(userName);
            }
        }
        return null;
    }
    
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

    public static boolean saveAccount(String firstName, String lastName, String userName,
            String password, String type) {
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
    
    public static Instructor passInstructor(String instructorId) {
        Accounts member = accountMap.get(instructorId);
        return (Instructor) member;
    }
    
    public static Student passStudent(String studentUserName) {
        Accounts member = accountMap.get(studentUserName);
        return (Student) member;
    }

    public static void loadClassesForInstructor(String instructorUserName) {
        Instructor instructor = (Instructor) accountMap.get(instructorUserName);
        ArrayList<Classes> classList = instructor.getClassesTaught();
        for (int i = 0; i < classList.size(); i++) {
            ViewClassesTaughtWindow.addClassToData(classList.get(i).getCRNAndName());
        }
    }

    public static void setMajorForStudent(String accountUserName, String selectedMajor) {
        Student student = (Student) accountMap.get(accountUserName);
        student.setMajor(MajorSaver.getMajor(selectedMajor));
    }

    public static boolean addClassToStudent(String userAccountName, String crn) {
        if(ClassSaver.passClassToView(crn) == null) {
            return false;
        }
        Classes classToAdd = ClassSaver.passClassToView(crn);
        Student studentAccount = AccountSaver.passStudent(userAccountName);
            Alert alert = new Alert(AlertType.ERROR);
        if (classToAdd == null) {
            alert.setTitle("No CRN Found");
            alert.setHeaderText("Error:");
            alert.setContentText("CRN is not available.");
            alert.showAndWait();
            return false;
        }
        if(studentAccount.addClassToCurrentClasses(classToAdd)) {
            ClassSaver.addStudentToClass(studentAccount, crn);
            return true;
        } else {
            alert.setTitle("Class already taken");
            alert.setHeaderText("Error:");
            alert.setContentText("You are already taking this class!");
            alert.showAndWait();
            return false;
        }
    }

    public static void loadClassesTaken(String userAccountName) {
        ArrayList<Classes> classesTaken = passStudent(userAccountName).getClassesTaken();
        for (Classes classes : classesTaken) {
            SainReportWindow.addClassToClassesTaken(classes.getCodeAndGPA(userAccountName));
        }
    }

    public static void loadClassesCurrentlyTaking(String userAccountName) {
        ArrayList<Classes> classesCurrentlyTaking = passStudent(userAccountName).getCurrentClasses();
        for (Classes classes : classesCurrentlyTaking) {
            SainReportWindow.addClassToCurrentlyTaking(classes.getNameAndCode());
        }
    }
}
