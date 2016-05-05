package window.admin;

import controller.AccountSaver;
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
public class RegisterAccountWindow {
   static Label firstNameLabel;
   static Label lastNameLabel;
   static Label userNameLabel;
   static Label passwordLabel;
   static TextField firstNameField;
   static TextField lastNameField;
   static TextField userNameField;
   static TextField passwordField;
   static RadioButton studentButton;
   static RadioButton instructorButton;
   static RadioButton adminButton;
   final static ToggleGroup group = new ToggleGroup();
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
        firstNameLabel = new Label("First Name: ");
        lastNameLabel = new Label("Last Name: ");
        userNameLabel = new Label("User Name: ");
        passwordLabel = new Label("Password: ");
        firstNameField = new TextField();
        lastNameField = new TextField();
        userNameField = new TextField();
        passwordField = new TextField();
        
        createRadioButtons();
        
        createButton = new Button("Create Account");
        createButton.setOnAction((ActionEvent event) -> {
            
            if(AccountSaver.saveAccount(firstNameField.getText(), lastNameField.getText(),
                    userNameField.getText(), passwordField.getText(),
                    (String) group.getSelectedToggle().getUserData())) {
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
            }
        });
        
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        
}
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, firstNameLabel, lastNameLabel, userNameLabel, passwordLabel, studentButton, instructorButton, adminButton, createButton);
        grid.addColumn(1, firstNameField, lastNameField, userNameField, passwordField, cancelButton);
        return grid;
    }

    private static void createRadioButtons() {
        studentButton = new RadioButton("Student");
        instructorButton = new RadioButton("Instructor");
        adminButton = new RadioButton("Admin");
        studentButton.setToggleGroup(group);
        studentButton.setSelected(true);
        instructorButton.setToggleGroup(group);
        adminButton.setToggleGroup(group);
        
        studentButton.setUserData("Student");
        instructorButton.setUserData("Instructor");
        adminButton.setUserData("Admin");
    }

}
