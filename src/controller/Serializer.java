package controller;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Serializer {
    public static void initializeFiles() {
        AccountSaver.initialize();
        MajorSaver.initialize();
        CourseSaver.initialize();
        ElectiveSaver.initialize();
        ClassSaver.initialize();
    }
    
    public static void saveFiles() {
        AccountSaver.save();
        MajorSaver.save();
        CourseSaver.save();
        ElectiveSaver.save();
        ClassSaver.save();
    }
}
