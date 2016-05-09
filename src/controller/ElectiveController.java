package controller;

import dataModel.Course;
import dataModel.Elective;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import window.admin.AddCourseToElectiveWindow;
import window.admin.ElectivePropertiesWindow;
import window.admin.ElectiveWindow;
import window.sainReport.SainViewElectiveWindow;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class ElectiveController {
    private static TreeMap<String, Elective> electiveMap;
    private static File file;

    /**
     * Load or create a file called ElectiveInfo.
     */
    public static void initialize() {
        file = new File("ElectiveInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                electiveMap = (TreeMap<String, Elective>) ois.readObject();
                ois.close();
                System.out.println("Electives loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Electives file created.");
                    initializeElectiveMap();
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
            oos.writeObject(electiveMap);
            oos.close();
            System.out.println("Electives saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void initializeElectiveMap() {
        electiveMap = new TreeMap<>();
        electiveMap.put("CST", new Elective("Computer Science", "CST"));
    }
    
    /**
     * Loads the electives to the list of data for the ElectiveWindow.
     */
    public static void loadElectivesToData() {
        electiveMap.entrySet().stream().forEach((entry) -> {
            ElectiveWindow.addElectiveToData(entry.getKey());
        });
    }
    
    /**
     * Loads the courses for a specified elective.
     * @param codeName The unique code name of the Elective.
     * @param window The window requesting the information.
     */
    public static void loadCoursesForElective(String codeName, String window) {
        ArrayList<Course> courseList = electiveMap.get(codeName).getCoursesInElective();
        if (window.equals("admin")) {
            for (int i = 0; i < courseList.size(); i++) {
                ElectivePropertiesWindow.addCourseToData(courseList.get(i).getCodeName());
            }
        } else if (window.equals("SAIN")) {
            for (int i = 0; i < courseList.size(); i++) {
                SainViewElectiveWindow.addCourseToData(courseList.get(i).getCodeName());
            }
        }
    }
    
    /**
     * Creates a new elective.
     * @param name
     * @param codeName
     * @return true if the elective doesn't already exist and the textfields are all full.
     */
    public static boolean addElective(String name, String codeName) {
        Alert alert = new Alert(AlertType.ERROR);
        if (name.isEmpty() || codeName.isEmpty()) {
            alert.setTitle("Empty Textfield Error");
            alert.setHeaderText("Error:");
            alert.setContentText("One or more of the textfields were empty.");
            alert.showAndWait();
            return false;
        }

        Elective newElective = new Elective(name, codeName);

        if (!electiveMap.containsKey(newElective.getCodeName())) {
            electiveMap.put(newElective.getCodeName(), newElective);
            save();
            ElectiveWindow.refreshListView();
            return true;
        }
        alert.setTitle("Taken Elective Codename Error");
        alert.setHeaderText("Error:");
        alert.setContentText("The Codename for the Elective " + newElective.getCodeName() + " has been taken");
        alert.showAndWait();
        return false;
    }

    /**
     * Deletes the specified elective from the list of all electives.
     * @param codeName
     * @return true if the elective is confirmed to be deleted.
     */
    public static boolean deleteElective(String codeName) {
        if (electiveMap.containsKey(codeName)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Major?");
            alert.setContentText("Are you sure you wish to delete " + electiveMap.get(codeName).getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean removed = electiveMap.remove(codeName, electiveMap.get(codeName));
                save();
                ElectiveWindow.refreshListView();
                return removed;
            }
        }
        // Codename wasnt acquired OR user closed the option dialog.
        return false;
    }

    /**
     * Passes the elective to the requested Window.
     * @param codeName
     * @return the Elective obtained by the codeName.
     */
    public static Elective passElectiveToView(String codeName) {
        return electiveMap.get(codeName).deepCopy();
    }
    
    /**
     * Passes the size of the list of all electives
     * @return the size of the list of all electives.
     */
    public static int passElectiveMapSize() {
        return electiveMap.size();
    }

    static ArrayList<Elective> obtainAllElectives() {
        ArrayList<Elective> electiveList = new ArrayList<>();
        for (Map.Entry<String, Elective> entry : electiveMap.entrySet()) {
            electiveList.add(entry.getValue());
        }
        return electiveList;
    }

    /**
     * Adds a specified course to the specified elective.
     * @param courseCodeName
     * @param electiveName
     * @return true if no issue is obtained.
     */
    public static boolean addCourse(String courseCodeName, String electiveName) {
        Course course = CourseController.passCourseToView(courseCodeName);
        try {
            electiveMap.get(electiveName).addCourseToElective(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Loads up all courses so as to be picked out by an elective.
     */
    public static void loadAllCourses() {
        ArrayList<Course> courseList = CourseController.obtainAllCourses();
        for (int i = 0; i < courseList.size(); i++) {
            AddCourseToElectiveWindow.addCourseToListOfAllData(courseList.get(i).getCodeName());
        }
    }
}