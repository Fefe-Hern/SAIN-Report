package window.sainReport;

import window.admin.*;
import controller.AccountController;
import controller.CourseController;
import controller.ElectiveController;
import controller.MajorController;
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
public class SainViewCourseWindow {
   static ListView<String> classList = new ListView<>();
   static ObservableList<String> data;
   static final Label CLASSNAMELABEL = new Label();
   static Label nameLabel;
   static Label codeNameLabel;
   static Label totalCreditsLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField totalCreditsField;
   static Button cancelButton;
    
   private static String courseCodeName;
   
    /**
     *
     * @param name
     * @return
     */
    public static Scene createScene(String name) {
        courseCodeName = name;
        createListView(courseCodeName);
        addFields();
        acquireCourseInfo();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Course Name: ");
        codeNameLabel = new Label("Code Name: ");
        totalCreditsLabel = new Label("Credits Given:");
        nameField = new TextField();
        codeNameField = new TextField();
        totalCreditsField = new TextField();
        nameField.setEditable(false);
        codeNameField.setEditable(false);
        totalCreditsField.setEditable(false);
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireCourseInfo() {
        nameField.setText(CourseController.passCourseToView(courseCodeName).getName());
        codeNameField.setText(CourseController.passCourseToView(courseCodeName).getCodeName());
        totalCreditsField.setText(String.valueOf(CourseController.passCourseToView(courseCodeName).getCreditsGiven()));
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, totalCreditsLabel);
        grid.addColumn(1, nameField, codeNameField, totalCreditsField, cancelButton);
        grid.addColumn(2, classList, CLASSNAMELABEL);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        CourseController.loadClassesForCourse(codeName, "SAIN");
        classList.setItems(data);
        classList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            CLASSNAMELABEL.setText(new_val);
        });
    }

    /**
     *
     * @param name
     */
    public static void addClassToData(String name) {
        data.add(name);
    }
    
    /**
     *
     */
    public static void refreshView() {
        createListView(courseCodeName);
        totalCreditsField.setText(String.valueOf(CourseController.passCourseToView(courseCodeName).getCreditsGiven()));
    }
}
