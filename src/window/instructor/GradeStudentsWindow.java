package window.instructor;

import window.admin.*;
import controller.AccountController;
import controller.ClassController;
import controller.CourseController;
import controller.ElectiveController;
import controller.MajorController;
import dataModel.Course;
import dataModel.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class GradeStudentsWindow {
   static Label gradeNameLabel = new Label();
   static TextField gradeNameField = new TextField();
   static Button gradeStudentButton;
    
   private static String classCodeName;
   private static int iterator;
   private static Student currentStudent;
   private static String currentStudentName;
   
    /**
     *
     * @param studentName
     * @param className
     * @return
     */
    public static Scene createScene(String studentName, String className) {
        currentStudentName = studentName;
        classCodeName = className;
        currentStudent = AccountController.passStudentByFullName(studentName);
        addFields();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 200, 200);
        return scene;
    }

    private static void addFields() {
        gradeNameLabel = new Label("Grade " + currentStudent.getFullName());
        gradeNameField = new TextField();
        gradeStudentButton = new Button("Grade Student");
        gradeStudentButton.setOnAction((ActionEvent event) -> {
            String graded = gradeNameField.getText();
            try {
                double gpa = Double.parseDouble(graded);
                if (0.0 <= gpa && gpa <= 4.0) {
                    ClassController.gradeStudentForClass(currentStudent, classCodeName, gpa);
                    Stage stage = (Stage) gradeStudentButton.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, gradeNameField, gradeNameLabel, gradeStudentButton);
        return grid;
    }

}
