package window.admin;

import controller.AccountSaver;
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
public class AddElectiveToMajorWindow {
   static ListView<String> electiveList = new ListView<>();
   static ListView<String> allElectives = new ListView<>(); // To move to electiveList
   static ObservableList<String> data;
   static ObservableList<String> dataOfAllElectives;
   static final Label ELECTIVENAMELABEL = new Label();
   static Label totalCreditsLabel;
   static TextField totalCreditsField;
   static Button cancelButton;
   static Button addElectiveToMajorButton;
    
   private static String majorCodeName;
   
    public static Scene createScene(String codeName) {
        majorCodeName = codeName;
        createListView(majorCodeName);
        createListOfAllElectives();
        addFields();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        totalCreditsLabel = new Label("Required Credits:");
        totalCreditsField = new TextField();

        addElectiveToMajorButton = new Button("Add New Elective");
        addElectiveToMajorButton.setOnAction((ActionEvent event) -> {
            try {
                int reqCredits = Integer.parseInt(totalCreditsField.getText());
                MajorSaver.addElectiveToMajor(majorCodeName, allElectives.getSelectionModel().getSelectedItem(), reqCredits);
            } catch (Exception e) {
                System.out.println("Credits is not numerical.");
            }
            Stage stage = (Stage) addElectiveToMajorButton.getScene().getWindow();
            MajorPropertiesWindow.refreshView();
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
        grid.addColumn(0, totalCreditsLabel);
        grid.addColumn(1, totalCreditsField, cancelButton);
        grid.addColumn(2, electiveList, ELECTIVENAMELABEL, allElectives, addElectiveToMajorButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        MajorSaver.loadElectivesForMajor(codeName, "Add Elective To Major Window");
        electiveList.setItems(data);
        electiveList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            ELECTIVENAMELABEL.setText(new_val);
        });
    }

    public static void addElectiveToData(String name) {
        data.add(name);
    }
    
    public static void refreshListView() {
        createListView(majorCodeName);
    }

    private static void createListOfAllElectives() {
        dataOfAllElectives = FXCollections.observableArrayList();
        MajorSaver.loadAllElectives();
        allElectives.setItems(dataOfAllElectives);
    }
    
    public static void addElectiveToListOfAllData(String name) {
        dataOfAllElectives.add(name);
    }
}
