package controller;

import java.util.HashMap;
import dataModel.Course;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
}
