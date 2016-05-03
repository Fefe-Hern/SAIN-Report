package window.instructor;

import window.admin.*;
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
public class ViewClassesTaughtWindow {
   static ListView<String> classList = new ListView<>();
   static ObservableList<String> data;
   static final Label CLASSNAMELABEL = new Label();
   static Button viewClassButton;
   static Button cancelButton;
    
   private static String instructorName;
   
    public static Scene createScene(String instructorUserName) {
        instructorName = instructorUserName;
        createListView(instructorName);
        addFields();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
        return scene;
    }

    private static void addFields() {
        viewClassButton = new Button("View Class");
        viewClassButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(ViewClassWindow.createScene(classList.getSelectionModel().getSelectedItem()));
            stage.show();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, classList, CLASSNAMELABEL, viewClassButton, cancelButton);
        return grid;
    }

    private static void createListView(String instructorUserName) {
        data = FXCollections.observableArrayList();
        AccountSaver.loadClassesForInstructor(instructorUserName);
        classList.setItems(data);
        classList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            CLASSNAMELABEL.setText(new_val);
        });
    }

    public static void addClassToData(String name) {
        data.add(name);
    }
    
    public static void refreshView() {
        createListView(instructorName);
    }
}
