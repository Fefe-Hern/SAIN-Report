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
public class CoursePropertiesWindow {
   static ListView<String> classList = new ListView<>();
   static ObservableList<String> data;
   static final Label CLASSNAMELABEL = new Label();
   static Label nameLabel;
   static Label codeNameLabel;
   static Label totalCreditsLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField totalCreditsField;
   static Button addClassToCourseButton;
   static Button cancelButton;
    
   private static String courseCodeName;
   
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
        
        addClassToCourseButton = new Button("Add New Class");
        addClassToCourseButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(AddClassToCourseWindow.createScene(courseCodeName));
            stage.show();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireCourseInfo() {
        nameField.setText(CourseSaver.passCourseToView(courseCodeName).getName());
        codeNameField.setText(CourseSaver.passCourseToView(courseCodeName).getCodeName());
        totalCreditsField.setText(
                String.valueOf(CourseSaver.passCourseToView(courseCodeName).getCreditsGiven()));
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, totalCreditsLabel);
        grid.addColumn(1, nameField, codeNameField, totalCreditsField, cancelButton);
        grid.addColumn(2, classList, CLASSNAMELABEL, addClassToCourseButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        CourseSaver.loadClassesForCourse(codeName);
        classList.setItems(data);
        classList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            CLASSNAMELABEL.setText(new_val);
        });
    }

    public static void addClassToData(String name) {
        data.add(name);
    }
    
    public static void refreshView() {
        createListView(courseCodeName);
        totalCreditsField.setText(String.valueOf(CourseSaver.passCourseToView(courseCodeName).getCreditsGiven()));
    }
}
