package window.admin;

import controller.AccountSaver;
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
   static Label creditsGivenLabel;
   static TextField creditsGivenField;
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
        creditsGivenLabel = new Label("Credits Given for Course");
        creditsGivenField = new TextField("4");
        
        addCourseToElectiveButton = new Button("Add New Course");
        addCourseToElectiveButton.setOnAction((ActionEvent event) -> {
            try {
                ElectiveSaver.addCourse(allCourses.getSelectionModel().getSelectedItem(), creditsGivenField.getText(), electiveCodeName);
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
        grid.addColumn(0, creditsGivenLabel);
        grid.addColumn(1, creditsGivenField, cancelButton);
        grid.addColumn(2, courseList, COURSENAMELABEL, allCourses, addCourseToElectiveButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        MajorSaver.loadElectivesForMajor(codeName, "Add Elective To Major Window");
        courseList.setItems(data);
        courseList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            COURSENAMELABEL.setText(new_val);
        });
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


/*package window.admin;

import controller.AccountSaver;
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

public class AddCourseToElectiveWindow {

    static Label nameLabel;
    static Label codeNameLabel;
    static Label creditsGivenLabel;
    static TextField nameField;
    static TextField codeNameField;
    static TextField creditsGivenField;
    static Button createButton;
    static Button cancelButton;

    private static String electiveCodeName;

    public static Scene createScene(String codeName) {
        electiveCodeName = codeName;
        addFields();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Class Name: ");
        codeNameLabel = new Label("Code Name: ");
        creditsGivenLabel = new Label("Credits Given: ");
        nameField = new TextField();
        codeNameField = new TextField();
        creditsGivenField = new TextField();

        createButton = new Button("Create Course");
        createButton.setOnAction((ActionEvent event) -> {
            String name = nameField.getText();
            String codeName = codeNameField.getText();
            String creditsGiven = creditsGivenField.getText();

            if (!name.isEmpty() && !codeName.isEmpty() && !creditsGiven.isEmpty()) {
                if (ElectiveSaver.addCourse(name, codeName, creditsGiven, electiveCodeName)) {
                    Stage stage = (Stage) createButton.getScene().getWindow();
                    stage.close();
                    ElectivePropertiesWindow.refreshListView();
                }
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
        grid.addColumn(0, nameLabel, codeNameLabel, creditsGivenLabel, createButton);
        grid.addColumn(1, nameField, codeNameField, creditsGivenField, cancelButton);
        return grid;
    }
}*/
