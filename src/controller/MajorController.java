package controller;

import dataModel.Elective;
import dataModel.Major;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import window.admin.AddElectiveToMajorWindow;
import window.admin.MajorPropertiesWindow;
import window.admin.MajorWindow;
import window.sainReport.SainReportWindow;
import window.sainReport.SainViewMajorsWindow;
import window.student.RegisterMajorWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class MajorController {
    private static TreeMap<String, Major> majorMap;
    private static File file;

    /**
     * Loads up or creates the MajorInfo file.
     */
    public static void initialize() {
        file = new File("MajorInfo.seq");
        if(file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                majorMap = (TreeMap<String, Major>) ois.readObject();
                ois.close();
                System.out.println("Majors loaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                if(file.createNewFile()) {
                    System.out.println("Major file created.");
                    initializeMajorMap();
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
            oos.writeObject(majorMap);
            oos.close();
            System.out.println("Majors saved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void initializeMajorMap() {
        majorMap = new TreeMap<>();
        majorMap.put("CST", new Major("CST", "Computer Science", 2.5));
    }
    
    /**
     * Creates a new Major to be added to the list of all Majors
     * @param name Name of Major
     * @param codeName Unique identification of Major
     * @param gpa The required GPA to graduate (not yet implemented)
     * @return true if the Major is added to the list of all Majors.
     */
    public static boolean addMajor(String name, String codeName, String gpa) {
        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        boolean match = Pattern.matches(decimalPattern, gpa);
        Alert alert = new Alert(AlertType.ERROR);
        if (name.isEmpty() || codeName.isEmpty() || gpa.isEmpty() || !match) {
            alert.setTitle("Empty Textfield / Incorrect Input Error");
            alert.setHeaderText("Error:");
            alert.setContentText("One or more of the textfields were empty or GPA isn't properly formatted.");
            alert.showAndWait();
            return false;
        }

        double gpaReq = Double.parseDouble(gpa);
        if (0 <= gpaReq && gpaReq <= 4.0 && match) {
            Major newMajor = new Major(codeName, name, Double.parseDouble(gpa));
            if (!majorMap.containsKey(newMajor.getCodeName())) {
                majorMap.put(newMajor.getCodeName(), newMajor);
                save();
                MajorWindow.refreshListView();
                return true;
            }
            alert.setTitle("Taken Major Codename Error");
            alert.setHeaderText("Error:");
            alert.setContentText("The Codename for the Major " + newMajor.getCodeName() + " has been taken");
            alert.showAndWait();
            return false;
        }
        alert.setTitle("GPA Numeration Error");
        alert.setHeaderText("Error:");
        alert.setContentText("The GPA is not between 0 and 4");
        alert.showAndWait();
        return false;
    }
    
    /**
     * Deletes specified major from the list of all majors
     * @param codeName
     * @return true if the major is removed from the list.
     */
    public static boolean deleteMajor(String codeName) {
        if(majorMap.containsKey(codeName)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Major?");
            alert.setContentText("Are you sure you wish to delete " + majorMap.get(codeName).getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean removed = majorMap.remove(codeName, majorMap.get(codeName));
                save();
                MajorWindow.refreshListView();
                return removed;
            }
        }
        // Codename wasnt acquired OR user closed the option dialog.
        return false;
    }
    
    /**
     * Returns the size of the list of all majors.
     * @return
     */
    public static int getMajorMapSize() {
        return majorMap.size();
    }

    /**
     * Load the list of all majors to be presented in a view
     * @param windowName
     */
    public static void loadMajorsToData(String windowName) {
        majorMap.entrySet().stream().forEach((entry) -> {
            if(windowName.equals("MajorWindow")) { MajorWindow.addMajorToData(entry.getKey()); }
            else if(windowName.equals("RegisterMajorWindow")) { RegisterMajorWindow.addMajorToData(entry.getKey()); }
            else if(windowName.equals("SAIN")) { SainViewMajorsWindow.addMajorToData(entry.getKey()); }
        });
    }

    /**
     * Loads the electives for a major to add electives to itself.
     * @param codeName
     * @param controller
     */
    public static void loadElectivesForMajor(String codeName, String controller) {
        ArrayList<Elective> electiveList = majorMap.get(codeName).getElectivesNeeded();
        if (controller.equals("Major Properties Window")) {
            for (int i = 0; i < electiveList.size(); i++) {
                MajorPropertiesWindow.addElectiveToData(electiveList.get(i).getCodeName());
            }
        }
        else if (controller.equals("Add Elective To Major Window")) {
            for (int i = 0; i < electiveList.size(); i++) {
                MajorPropertiesWindow.addElectiveToData(electiveList.get(i).getCodeName());
            }
        }
    }
    
    /**
     * Loads up the electives required to 'graduate'.
     * @param majorName The major whose electives are being loaded up.
     */
    public static void loadElectivesForSain(String majorName) {
        Major major = majorMap.get(majorName);
        ArrayList<Elective> electiveList = major.getElectivesNeeded();
        Elective elective;
        for (int i = 0; i < electiveList.size(); i++) {
            elective = electiveList.get(i);
            SainReportWindow.addElectiveToCoursesNeeded(elective.getCreditsRequired() + 
                    " credits for " + elective.getName() + "(" + elective.getCodeName() + ")");
        }
        
    }

    /**
     * Loads all of the electives for a major to add.
     */
    public static void loadAllElectives() {
        ArrayList<Elective> electiveList = ElectiveController.obtainAllElectives();
        for (int i = 0; i < electiveList.size(); i++) {
            AddElectiveToMajorWindow.addElectiveToListOfAllData(electiveList.get(i).getCodeName());
        }
    }

    /**
     * Changes the required GPA for a major.
     * @param codeName
     * @param newGpa
     * @return true if the major's new GPA is a numerical value between 0 and 4.
     */
    public static boolean editGPA(String codeName, String newGpa) {
        try {
            double gpaCheck = Double.parseDouble(newGpa);
            if (0 <= gpaCheck && gpaCheck <= 4.0) {
                majorMap.get(codeName).setGPAreq(gpaCheck);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    /**
     * Passes the major to a window for viewing.
     * @param codeName
     * @return the Major specified by codeName.
     */
    public static Major passMajorToView(String codeName) {
        return majorMap.get(codeName);
    }

    /**
     * Add a new elective to specified major.
     * @param majorCodeName The Major which will have a new elective added.
     * @param selectedItem New Elective to be added
     * @param reqCredits The amount of credits required to 'complete' the elective.
     */
    public static void addElectiveToMajor(String majorCodeName, String selectedItem, int reqCredits) {
        Elective elective = ElectiveController.passElectiveToView(selectedItem);
        elective.setCreditsRequired(reqCredits);
        majorMap.get(majorCodeName).addElectiveToMajor(elective);
        majorMap.get(majorCodeName).setTotalCredits(majorMap.get(majorCodeName).getTotalCredits() + reqCredits);
    }
    
    /**
     * Removes specified elective from major.
     * @param majorCodeName
     * @param selectedItem The elective to be removed.
     */
    public static void removeElectiveFromMajor(String majorCodeName, String selectedItem) {
        Elective elective = ElectiveController.passElectiveToView(selectedItem);
        majorMap.get(majorCodeName).removeElectiveFromMajor(elective);
    }

    static Major getMajor(String selectedMajor) {
        return majorMap.get(selectedMajor);
    }


}
