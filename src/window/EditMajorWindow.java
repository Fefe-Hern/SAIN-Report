package window;

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
public class EditMajorWindow {
   static ListView<String> electiveList = new ListView<>();
   static ListView<String> allElectives = new ListView<>(); // To move to electiveList
   static ObservableList<String> data;
   static ObservableList<String> dataOfAllElectives;
   static final Label ELECTIVENAMELABEL = new Label();
   static Label nameLabel;
   static Label codeNameLabel;
   static Label gpaReqLabel;
   static Label totalCreditsLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField gpaField;
   static TextField totalCreditsField;
   static Button changeGpaButton;
   static Button cancelButton;
   static Button addElectiveToMajorButton;
    
   private static String codeName;
   
    public static Scene createScene(String name) {
        codeName = name;
        createListView(codeName);
        createListOfAllElectives();
        addFields();
        acquireMajorInfo();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Major Name: ");
        codeNameLabel = new Label("Code Name: ");
        gpaReqLabel = new Label("GPA Req.: ");
        totalCreditsLabel = new Label("Total Credits Req.:");
        nameField = new TextField();
        codeNameField = new TextField();
        gpaField = new TextField();
        totalCreditsField = new TextField();
        nameField.setEditable(false);
        codeNameField.setEditable(false);
        totalCreditsField.setEditable(false);
        
        changeGpaButton = new Button("Change GPA Req");
        changeGpaButton.setOnAction((ActionEvent event) -> {
            if(!MajorSaver.editGPA(codeNameField.getText(), gpaField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GPA Numerical Error");
                alert.setHeaderText("Error:");
                alert.setContentText("The GPA is not a number between 0 and 4.0");
                alert.showAndWait();
            }
        });
        
        addElectiveToMajorButton = new Button("Add New Elective");
        addElectiveToMajorButton.setOnAction((ActionEvent event) -> {
            
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireMajorInfo() {
        nameField.setText(MajorSaver.passMajorToView(codeName).getName());
        codeNameField.setText(MajorSaver.passMajorToView(codeName).getCodeName());
        gpaField.setText(
                String.valueOf(MajorSaver.passMajorToView(codeName).getGPAreq()));
        totalCreditsField.setText(
                String.valueOf(MajorSaver.passMajorToView(codeName).getTotalCredits()));
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, gpaReqLabel, totalCreditsLabel, changeGpaButton);
        grid.addColumn(1, nameField, codeNameField, gpaField, totalCreditsField, cancelButton);
        grid.addColumn(2, electiveList, ELECTIVENAMELABEL);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        MajorSaver.loadElectivesForMajor(codeName);
        electiveList.setItems(data);
        electiveList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            ELECTIVENAMELABEL.setText(new_val);
        });
    }

    public static void addElectiveToData(String name) {
        data.add(name);
    }
    
    public static void refreshListView() {
        createListView(codeName);
    }

    private static void createListOfAllElectives() {
        dataOfAllElectives = FXCollections.observableArrayList();
        MajorSaver.loadAllElectives();
        allElectives.setItems(data);
    }
    
    public static void addElectiveToListOfAllData(String name) {
        
    }
}
