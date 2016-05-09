package window.student;

import window.admin.*;
import controller.AccountController;
import controller.ClassController;
import controller.MajorController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 * @author fefe
 */
public class RegisterClassesWindow {
   static Label crnLabel;
   static TextField crnField;
   static Button registerButton;
   static Button cancelButton;
   
   static String userAccountName;
    
    /**
     *
     * @param userName
     * @return
     */
    public static Scene createScene(String userName) {
        userAccountName = userName;
        addFields();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        crnLabel = new Label("CRN: ");
        crnField = new TextField();
        
        registerButton = new Button("Register");
        registerButton.setOnAction((ActionEvent event) -> {
            if (AccountController.addClassToStudent(userAccountName, crnField.getText())) {
                Stage stage = (Stage) registerButton.getScene().getWindow();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Class registered");
                alert.setHeaderText(ClassController.passClassToView(crnField.getText()).getNameAndCode());
                alert.setContentText("Class has been registered. Professor is " + ClassController.passClassToView(
                        crnField.getText()).getInstructor().getFullName());
                alert.showAndWait();
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
        grid.addColumn(0, crnLabel, registerButton);
        grid.addColumn(1, crnField, cancelButton);
        return grid;
    }
}
