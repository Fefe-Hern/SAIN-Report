package controller;

import dataModel.Classes;
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
import window.instructor.ViewClassWindow;
/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class ClassController {
    private static TreeMap<String, Classes> classMap;
    private static File file;

    /**
     * Called to load up ClassInfo or create a new file named ClassInfo.
     */
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

    /**
     * Sends the class that is specified by its CRN.
     * @param classCRNAndName the CRN and name. The CRN will be extracted from this.
     * @return the class specified by its corresponding CRN.
     */
    public static Classes passClassToView(String classCRNAndName) {
        if(classCRNAndName.length() < 5) return null;
        String crn = classCRNAndName.substring(0, 5);
        return classMap.get(crn);
    }
    
    /**
     * Sends the list of students for the specified class to the View's Data List.
     * @param classCRNAndName
     */
    public static void loadStudentsForClass(String classCRNAndName) {
        String crn = classCRNAndName.substring(0, 5);
        ArrayList<Student> studentList = classMap.get(crn).getStudentList();
        for (int i = 0; i < studentList.size(); i++) {
            ViewClassWindow.addStudentToData(studentList.get(i).getFirstName() + " " + 
                    studentList.get(i).getLastName());
        }
    }
    
    /**
     * Obtains every class that had been created.
     * @return the list of Classes.
     */
    public static ArrayList<Classes> obtainAllClasses() {
        ArrayList<Classes> classList = new ArrayList<>();
        for (Map.Entry<String, Classes> entry : classMap.entrySet()) {
            classList.add(entry.getValue());
        }
        return classList;
    }
    
    /**
     * Saves the Class specified to the list of Classes
     * @param classToSave
     */
    public static void saveClass(Classes classToSave) {
        String crn = classToSave.getCrn();
        classMap.put(classToSave.getCrn(), classToSave);
    }

    /**
     * Adds the student to the specified Class.
     * @param studentToAdd
     * @param crn
     */
    public static void addStudentToClass(Student studentToAdd, String crn) {
        classMap.get(crn).getStudentList().add(studentToAdd);
        classMap.get(crn).getGpaList().add(4.0);
    }

    static boolean crnTaken(String crn) {
        return classMap.containsKey(crn);
    }

    /**
     * Gives the specified student the corresponding grade for the class.
     * @param currentStudent
     * @param classCodeName
     * @param grade
     */
    public static void gradeStudentForClass(Student currentStudent, String classCodeName, double grade) {
        String crn = classCodeName.substring(0, 5);
        Classes classToGrade = classMap.get(crn);
        int iterator = 1000;
        for (int i = 0; i < classToGrade.getStudentList().size(); i++) {
            if(classToGrade.getStudentList().get(i).getUserName().equals(currentStudent.getUserName())) {
                iterator = i;
            }
        }
        classToGrade.getGpaList().set(iterator, grade);
    }

    /**
     * Empty the classroom; set class to completed for all students and remove it from instructor's current classes taught.
     * @param classCodeName the class that had finished.
     */
    public static void finishClass(String classCodeName) {
        String crn = classCodeName.substring(0, 5);
        Classes classToClear = classMap.get(crn);
        ArrayList<Student> studentList = classToClear.getStudentList();
        for (Student student : studentList) {
            AccountController.finishClass(student, classToClear);
        }
        AccountController.removeInstructorTeachingClass(classToClear.getInstructor(), crn);
        classToClear.getInstructor().getClassesTaught().remove(classToClear);
        classMap.replace(crn, classToClear);
    }

}
