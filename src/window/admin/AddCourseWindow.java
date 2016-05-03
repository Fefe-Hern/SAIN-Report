package window.admin;

import controller.AccountSaver;
import controller.CourseSaver;
import controller.ElectiveSaver;
import controller.MajorSaver;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class AddCourseWindow {
   static Label nameLabel;
   static Label codeNameLabel;
   static Label creditsGivenLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField creditsGivenField;
   static Button createButton;
   static Button cancelButton;
    
    public static Scene createScene() {
        addFields();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Course Name: ");
        codeNameLabel = new Label("Code Name: ");
        creditsGivenLabel = new Label("Credits Given: ");
        nameField = new TextField();
        codeNameField = new TextField();
        creditsGivenField = new TextField();
        
        createButton = new Button("Create Course");
        createButton.setOnAction((ActionEvent event) -> {
            if(CourseSaver.addCourse(nameField.getText(), codeNameField.getText(), creditsGivenField.getText())) {
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
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
        grid.addColumn(0, nameLabel, codeNameLabel, creditsGivenLabel, createButton);
        grid.addColumn(1, nameField, codeNameField, creditsGivenField, cancelButton);
        return grid;
    }
}
