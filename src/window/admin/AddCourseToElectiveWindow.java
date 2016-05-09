package window.admin;

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
public class AddCourseToElectiveWindow {
   static ListView<String> allCourses = new ListView<>(); // To move to electiveList
   static ObservableList<String> dataOfAllCourses;
   static final Label COURSENAMELABEL = new Label();
   static Button cancelButton;
   static Button addCourseToElectiveButton;
    
   private static String electiveCodeName;
   
    /**
     *
     * @param codeName
     * @return
     */
    public static Scene createScene(String codeName) {
        electiveCodeName = codeName;
        createListOfAllCourses();
        addFields();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        addCourseToElectiveButton = new Button("Add To Elective");
        addCourseToElectiveButton.setOnAction((ActionEvent event) -> {
            try {
                ElectiveController.addCourse(allCourses.getSelectionModel().getSelectedItem(), electiveCodeName);
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
        grid.addColumn(0, COURSENAMELABEL, allCourses, addCourseToElectiveButton, cancelButton);
        return grid;
    }

    private static void createListOfAllCourses() {
        dataOfAllCourses = FXCollections.observableArrayList();
        ElectiveController.loadAllCourses();
        allCourses.setItems(dataOfAllCourses);
    }
    
    /**
     *
     * @param name
     */
    public static void addCourseToListOfAllData(String name) {
        dataOfAllCourses.add(name);
    }
}