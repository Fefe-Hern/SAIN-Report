package window.admin;

import controller.AccountController;
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
public class ElectivePropertiesWindow {
   static ListView<String> courseList = new ListView<>();
   static ObservableList<String> data;
   static final Label COURSENAMELABEL = new Label();
   static Label nameLabel;
   static Label codeNameLabel;
   static Label totalCreditsLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField totalCreditsField;
   static Button addCourseToElectiveButton;
   static Button cancelButton;
    
   private static String codeName;
   
    /**
     *
     * @param name
     * @return
     */
    public static Scene createScene(String name) {
        codeName = name;
        createListView(codeName);
        addFields();
        acquireElectiveInfo();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Elective Name: ");
        codeNameLabel = new Label("Code Name: ");
        totalCreditsLabel = new Label("Total Credits:");
        nameField = new TextField();
        codeNameField = new TextField();
        totalCreditsField = new TextField();
        nameField.setEditable(false);
        codeNameField.setEditable(false);
        totalCreditsField.setEditable(false);
        
        addCourseToElectiveButton = new Button("Add New Course");
        addCourseToElectiveButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(AddCourseToElectiveWindow.createScene(codeName));
            stage.show();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireElectiveInfo() {
        nameField.setText(ElectiveController.passElectiveToView(codeName).getName());
        codeNameField.setText(ElectiveController.passElectiveToView(codeName).getCodeName());
        totalCreditsField.setText(String.valueOf(ElectiveController.passElectiveToView(codeName).getTotalCreditsOfCourses()));
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, totalCreditsLabel);
        grid.addColumn(1, nameField, codeNameField, totalCreditsField, cancelButton);
        grid.addColumn(2, courseList, COURSENAMELABEL, addCourseToElectiveButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        ElectiveController.loadCoursesForElective(codeName, "admin");
        courseList.setItems(data);
        courseList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            COURSENAMELABEL.setText(new_val);
        });
    }

    /**
     *
     * @param name
     */
    public static void addCourseToData(String name) {
        data.add(name);
    }
    
    /**
     *
     */
    public static void refreshView() {
        createListView(codeName);
        totalCreditsField.setText(String.valueOf(ElectiveController.passElectiveToView(codeName).getTotalCreditsOfCourses()));
    }
}
