package window.student;

import window.admin.*;
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
public class RegisterClassesWindow {
   static Label crnLabel;
   static TextField crnField;
   static Button registerButton;
   static Button cancelButton;
   
   static String userAccountName;
    
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
            if(AccountSaver.addClassToStudent(userAccountName, crnField.getText())) {
                Stage stage = (Stage) registerButton.getScene().getWindow();
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
