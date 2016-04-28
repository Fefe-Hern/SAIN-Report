package window;

import controller.AccountSaver;
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
public class AddElectiveWindow {
   static Label nameLabel;
   static Label codeNameLabel;
   static TextField nameField;
   static TextField codeNameField;
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
        nameLabel = new Label("Elective Name: ");
        codeNameLabel = new Label("Code Name (Key Field): ");
        nameField = new TextField();
        codeNameField = new TextField();
        
        createButton = new Button("Create Elective");
        createButton.setOnAction((ActionEvent event) -> {
            if(ElectiveSaver.addElective(nameField.getText(), codeNameField.getText())) {
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
        grid.addColumn(0, nameLabel, codeNameLabel, createButton);
        grid.addColumn(1, nameField, codeNameField, cancelButton);
        return grid;
    }
}
