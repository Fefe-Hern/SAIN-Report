package controller;

import dataModel.Major;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import window.EditMajorWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class MajorSaver {
    //private static HashMap<String, Major> majorMap;
    private static TreeMap<String, Major> majorMap;
    private static File file;
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
    
    public static boolean addMajor(String name, String codeName, String gpa) {
        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        boolean match = Pattern.matches(decimalPattern, gpa);

        if (name.isEmpty() || codeName.isEmpty() || gpa.isEmpty() || !match) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Empty Textfield / Incorrect Input Error");
            alert.setHeaderText("Error:");
            alert.setContentText("One or more of the textfields were empty or GPA isn't properly formatted.");
            alert.showAndWait();
            return false;
        }
        
        Major newMajor = new Major(codeName, name, Double.parseDouble(gpa));
        if(!majorMap.containsKey(newMajor.getCodeName())) {
            majorMap.put(newMajor.getCodeName(), newMajor);
            save();
            EditMajorWindow.refreshListView();
            return true;
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Taken Major Codename Error");
        alert.setHeaderText("Error:");
        alert.setContentText("The Codename for the Major " + newMajor.getCodeName() + " has been taken");
        alert.showAndWait();
        return false;
    }
    
    public static int getMajorMapSize() {
        return majorMap.size();
    }

    public static void loadMajorsToData() {
        majorMap.entrySet().stream().forEach((entry) -> {
            EditMajorWindow.addMajorToData(entry.getKey());
        });
    }
}
