package window.admin;

import controller.AccountController;
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
public class MajorPropertiesWindow {
   static ListView<String> electiveList = new ListView<>();
   static ObservableList<String> data;
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
   static Button removeElectiveFromMajorButton;
    
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
        acquireMajorInfo();
        GridPane grid = createLayout();
        Button btn = new Button();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
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
            if(!MajorController.editGPA(codeNameField.getText(), gpaField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GPA Numerical Error");
                alert.setHeaderText("Error:");
                alert.setContentText("The GPA is not a number between 0 and 4.0");
                alert.showAndWait();
            }
        });
        
        addElectiveToMajorButton = new Button("Add New Elective");
        addElectiveToMajorButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(AddElectiveToMajorWindow.createScene(codeName));
            stage.show();
        });
        
        removeElectiveFromMajorButton = new Button("Remove Elective");
        removeElectiveFromMajorButton.setOnAction((ActionEvent event) -> {
            MajorController.removeElectiveFromMajor(codeName, electiveList.getSelectionModel().getSelectedItem());
            refreshView();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireMajorInfo() {
        nameField.setText(MajorController.passMajorToView(codeName).getName());
        codeNameField.setText(MajorController.passMajorToView(codeName).getCodeName());
        gpaField.setText(String.valueOf(MajorController.passMajorToView(codeName).getGPAreq()));
        totalCreditsField.setText(String.valueOf(MajorController.passMajorToView(codeName).getTotalCredits()));
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, gpaReqLabel, totalCreditsLabel, changeGpaButton);
        grid.addColumn(1, nameField, codeNameField, gpaField, totalCreditsField, cancelButton);
        grid.addColumn(2, electiveList, ELECTIVENAMELABEL, addElectiveToMajorButton, removeElectiveFromMajorButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        MajorController.loadElectivesForMajor(codeName, "Major Properties Window");
        electiveList.setItems(data);
        electiveList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            ELECTIVENAMELABEL.setText(new_val);
        });
    }

    /**
     *
     * @param name
     */
    public static void addElectiveToData(String name) {
        data.add(name);
    }
    
    /**
     *
     */
    public static void refreshView() {
        createListView(codeName);
        totalCreditsField.setText(String.valueOf(MajorController.passMajorToView(codeName).getTotalCredits()));
    }
}
