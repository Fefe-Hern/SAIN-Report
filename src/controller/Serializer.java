package controller;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class Serializer {
    static void initializeFiles() {
        AccountSaver.initialize();
        MajorSaver.initialize();
        CourseSaver.initialize();
    }
    
    static void saveFiles() {
        AccountSaver.save();
        MajorSaver.save();
        CourseSaver.save();
    }
}
