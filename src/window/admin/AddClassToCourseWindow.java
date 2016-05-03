package window.admin;

import controller.AccountSaver;
import controller.CourseSaver;
import controller.ElectiveSaver;
import controller.MajorSaver;
import dataModel.Course;
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
public class AddClassToCourseWindow {
   private static Label instructorUserNameLabel;
   private static Label crnLabel;
   private static TextField crnField;
   private static TextField instructorUserNameField;
   static Button addClassToCourseButton;
   static Button cancelButton;
    
   private static String courseCodeName;
   
    public static Scene createScene(String codeName) {
        courseCodeName = codeName;
        addFields();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        instructorUserNameLabel = new Label("Username of Instructor");
        crnLabel = new Label("Unique CRN:");
        instructorUserNameField = new TextField();
        crnField = new TextField();
        
        addClassToCourseButton = new Button("Create Class");
        addClassToCourseButton.setOnAction((ActionEvent event) -> {
            String instructorUserName = instructorUserNameField.getText();
            String crn = crnField.getText();
            if(!instructorUserName.isEmpty() && !crn.isEmpty()) {
                if(AccountSaver.searchForInstructor(instructorUserName)) {
                    CourseSaver.createClassForCourse(courseCodeName, crn, instructorUserName);
                    Stage stage = (Stage) addClassToCourseButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, instructorUserNameLabel, crnLabel, addClassToCourseButton);
        grid.addColumn(1, instructorUserNameField, crnField, cancelButton);
        return grid;
    }

}