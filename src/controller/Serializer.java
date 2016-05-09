package controller;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Serializer {

    /**
     * Initializes all the proper files so as to be loaded up into data. Otherwise, new files are created.
     */
    public static void initializeFiles() {
        AccountController.initialize();
        MajorController.initialize();
        CourseController.initialize();
        ElectiveController.initialize();
        ClassController.initialize();
    }
    
    /**
     * Saves all of the hash/treemaps to the files specified.
     */
    public static void saveFiles() {
        AccountController.save();
        MajorController.save();
        CourseController.save();
        ElectiveController.save();
        ClassController.save();
    }
}
