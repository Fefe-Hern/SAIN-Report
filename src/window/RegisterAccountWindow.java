package window;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
   static Label idLabel;
   static TextField firstNameField;
   static TextField lastNameField;
   static TextField userNameField;
   static TextField passwordField;
   static TextField idField;
   static RadioButton radioButton;
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
        idLabel = new Label("ID: ");
        firstNameField = new TextField();
        lastNameField = new TextField();
        userNameField = new TextField();
        passwordField = new TextField();
        idField = new TextField();
        
        createButton = new Button("Create Account");
        createButton.setOnAction((ActionEvent event) -> {
            System.out.println("Good Job");
        });
        
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        
}
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, firstNameLabel, lastNameLabel, userNameLabel, passwordLabel, idLabel, createButton);
        grid.addColumn(1, firstNameField, lastNameField, userNameField, passwordField, idField, cancelButton);
        return grid;
    }

}
