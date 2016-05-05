package controller;

import dataModel.Classes;
import java.util.HashMap;
import dataModel.Course;
import dataModel.Instructor;
import dataModel.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import window.admin.CoursePropertiesWindow;
import window.admin.CourseWindow;
import window.instructor.ViewClassWindow;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class ClassSaver {
    private static TreeMap<String, Classes> classMap;
    private static File file;
    public static void initialize() {
        file = new File("ClassInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                classMap = (TreeMap<String, Classes>) ois.readObject();
                ois.close();
                System.out.println("Class loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Classes file created.");
                    initializeClassesMap();
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
            oos.writeObject(classMap);
            oos.close();
            System.out.println("Classes saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void initializeClassesMap() {
        classMap = new TreeMap<>();
    }

    public static Classes passClassToView(String classCRNAndName) {
        if(classCRNAndName.length() < 5) return null;
        String crn = classCRNAndName.substring(0, 5);
        return classMap.get(crn);
    }
    
    public static void loadStudentsForClass(String classCRNAndName) {
        String crn = classCRNAndName.substring(0, 5);
        ArrayList<Student> studentList = classMap.get(crn).getStudentList();
        for (int i = 0; i < studentList.size(); i++) {
            ViewClassWindow.addStudentToData(studentList.get(i).getFirstName() + " " + 
                    studentList.get(i).getLastName());
        }
    }
    
    public static ArrayList<Classes> obtainAllClasses() {
        ArrayList<Classes> classList = new ArrayList<>();
        for (Map.Entry<String, Classes> entry : classMap.entrySet()) {
            classList.add(entry.getValue());
        }
        return classList;
    }
    
    
    public static void saveClass(Classes classToSave) {
        String crn = classToSave.getCrn();
        classMap.put(classToSave.getCrn(), classToSave);
    }

    public static void addStudentToClass(Student studentToAdd, String crn) {
        classMap.get(crn).getStudentList().add(studentToAdd);
        classMap.get(crn).getGpaList().add(4.0);
    }

    static boolean crnTaken(String crn) {
        return classMap.containsKey(crn);
    }
}
