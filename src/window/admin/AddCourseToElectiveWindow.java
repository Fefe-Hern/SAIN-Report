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
public class AddCourseToElectiveWindow {
   static ListView<String> courseList = new ListView<>();
   static ListView<String> allCourses = new ListView<>(); // To move to electiveList
   static ObservableList<String> data;
   static ObservableList<String> dataOfAllCourses;
   static final Label COURSENAMELABEL = new Label();
   static Button cancelButton;
   static Button addCourseToElectiveButton;
    
   private static String electiveCodeName;
   
    public static Scene createScene(String codeName) {
        electiveCodeName = codeName;
        createListView(electiveCodeName);
        createListOfAllCourses();
        addFields();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        addCourseToElectiveButton = new Button("Add New Course");
        addCourseToElectiveButton.setOnAction((ActionEvent event) -> {
            try {
                ElectiveSaver.addCourse(allCourses.getSelectionModel().getSelectedItem(), electiveCodeName);
            } catch (Exception e) {
                System.out.println("Credits is not numerical.");
            }
            Stage stage = (Stage) addCourseToElectiveButton.getScene().getWindow();
            ElectivePropertiesWindow.refreshView();
            stage.close();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, courseList, COURSENAMELABEL, allCourses, addCourseToElectiveButton, cancelButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        ElectiveSaver.loadCoursesForElective(codeName);
        courseList.setItems(data);
    }

    public static void addElectiveToData(String name) {
        data.add(name);
    }
    
    public static void refreshListView() {
        createListView(electiveCodeName);
    }

    private static void createListOfAllCourses() {
        dataOfAllCourses = FXCollections.observableArrayList();
        ElectiveSaver.loadAllCourses();
        allCourses.setItems(dataOfAllCourses);
    }
    
    public static void addCourseToListOfAllData(String name) {
        dataOfAllCourses.add(name);
    }
}