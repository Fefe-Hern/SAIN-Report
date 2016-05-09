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
import window.sainReport.SainViewCourseWindow;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class CourseController {
    private static HashMap<String, Course> courseMap;
    private static File file;

    /**
     * Finds or creates the file named CourseInfo.
     */
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

    /**
     * Passes the course to the window requesting it.
     * @param courseCodeName
     * @return information on the Course.
     */
    public static Course passCourseToView(String courseCodeName) {
        return courseMap.get(courseCodeName).deepCopy();
    }
    
    /**
     * Loads up all of the classes specified by the Course. For instance, CST has CST 112 and CST 222.
     * @param courseCodeName
     * @param window The window asking for the information.
     */
    public static void loadClassesForCourse(String courseCodeName, String window) {
        ArrayList<Classes> classList = courseMap.get(courseCodeName).getClassesInCourse();
        if (window.equals("admin")) {
            for (int i = 0; i < classList.size(); i++) {
                CoursePropertiesWindow.addClassToData(classList.get(i).getCRNAndInstructor());
            }
        }
        if (window.equals("SAIN")) {
            for (int i = 0; i < classList.size(); i++) {
                SainViewCourseWindow.addClassToData(classList.get(i).getCRNAndInstructor());
            }
        }
    }
    
    /**
     * Loads up the courses to the proper Course Window list.
     */
    public static void loadCoursesToData() {
        courseMap.entrySet().stream().forEach((entry) -> {
            CourseWindow.addCourseToData(entry.getKey());
        });
    }
    
    /**
     * Obtain the list of all of the courses.
     * @return the list of all courses.
     */
    public static ArrayList<Course> obtainAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        for (Map.Entry<String, Course> entry : courseMap.entrySet()) {
            courseList.add(entry.getValue());
        }
        return courseList;
    }
    
    /**
     * Adds the course to the list of courses.
     * @param name
     * @param codeName
     * @param creditsReq
     * @return true if no similar course exists and all the textfields are full.
     */
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

    /**
     * Creates a brand new 'classroom' for the course specified.
     * @param courseCodeName
     * @param crn The class unique identification.
     * @param instructorId the instructor's username who is assigned to this class.
     * @return
     */
    public static boolean createClassForCourse(String courseCodeName, String crn, String instructorId) {
        if(crn.length() != 5) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("CRN Error");
            alert.setHeaderText("Error:");
            alert.setContentText("The CRN " + crn + " must be 5 characters exact.");
            alert.showAndWait();
            return false;
        }
        if (ClassController.crnTaken(crn)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Taken CRN Error");
            alert.setHeaderText("Error:");
            alert.setContentText("The CRN " + crn + " has been taken.");
            alert.showAndWait();
            return false;
        }
        Instructor instructor = AccountController.passInstructor(instructorId);
        courseMap.get(courseCodeName).addClass(instructor, crn);
        CoursePropertiesWindow.refreshView();
        return true;
    }
}
