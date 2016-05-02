package window.admin;

import controller.AccountSaver;
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
public class AddMajorWindow {
   static Label nameLabel;
   static Label codeNameLabel;
   static Label gpaReqLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField gpaField;
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
        nameLabel = new Label("Major Name: ");
        codeNameLabel = new Label("Code Name (Key Field): ");
        gpaReqLabel = new Label("GPA Requirement: ");
        nameField = new TextField();
        codeNameField = new TextField();
        gpaField = new TextField();
        
        createButton = new Button("Create Major");
        createButton.setOnAction((ActionEvent event) -> {
            if(MajorSaver.addMajor(nameField.getText(), codeNameField.getText(), gpaField.getText())) {
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
        grid.addColumn(0, nameLabel, codeNameLabel, gpaReqLabel, createButton);
        grid.addColumn(1, nameField, codeNameField, gpaField, cancelButton);
        return grid;
    }
}
