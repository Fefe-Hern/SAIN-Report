package controller;

import dataModel.Classes;
import java.util.HashMap;
import dataModel.Course;
import dataModel.Instructor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import window.admin.CoursePropertiesWindow;
import window.admin.CourseWindow;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class CourseSaver {
    private static HashMap<String, Course> courseMap;
    private static File file;
    public static void initialize() {
        file = new File("CourseInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                courseMap = (HashMap<String, Course>) ois.readObject();
                ois.close();
                System.out.println("Courses loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Courses file created.");
                    initializeCourseMap();
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
            oos.writeObject(courseMap);
            oos.close();
            System.out.println("Courses saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void initializeCourseMap() {
        courseMap = new HashMap<>();
        courseMap.put("CST 101", new Course("Introduction to Java", "CST 101", 4));
    }

    public static Course passCourseToView(String courseCodeName) {
        return courseMap.get(courseCodeName).deepCopy();
    }
    
    public static void loadClassesForCourse(String courseCodeName) {
        ArrayList<Classes> classList = courseMap.get(courseCodeName).getClassesInCourse();
        for (int i = 0; i < classList.size(); i++) {
            CoursePropertiesWindow.addClassToData(classList.get(i).getCRNAndInstructor());
        }
    }
    
    public static void loadCoursesToData() {
        courseMap.entrySet().stream().forEach((entry) -> {
            CourseWindow.addCourseToData(entry.getKey());
        });
    }
    
    public static ArrayList<Course> obtainAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        for (Map.Entry<String, Course> entry : courseMap.entrySet()) {
            courseList.add(entry.getValue());
        }
        return courseList;
    }
    
    
    public static boolean addCourse(String name, String codeName, String creditsReq) {
        Alert alert = new Alert(AlertType.ERROR);
        if (name.isEmpty() || codeName.isEmpty() || creditsReq.isEmpty()) {
            alert.setTitle("Empty Textfield Error");
            alert.setHeaderText("Error:");
            alert.setContentText("One or more of the textfields were empty.");
            alert.showAndWait();
            return false;
        }
        
        
        Course newCourse;
        try {

            newCourse = new Course(name, codeName, Integer.parseInt(creditsReq));
        } catch (Exception e) {
            return false;
        }

        if (!courseMap.containsKey(newCourse.getCodeName())) {
            courseMap.put(newCourse.getCodeName(), newCourse);
            save();
            CourseWindow.refreshListView();
            return true;
        }
        alert.setTitle("Taken Elective Codename Error");
        alert.setHeaderText("Error:");
        alert.setContentText("The Codename for the Elective " + newCourse.getCodeName() + " has been taken");
        alert.showAndWait();
        return false;
    }

    public static void createClassForCourse(String courseCodeName, String crn, String instructorId) {
        Instructor instructor = AccountSaver.passInstructor(instructorId);
        courseMap.get(courseCodeName).addClass(instructor, crn);
        CoursePropertiesWindow.refreshView();
    }
}
