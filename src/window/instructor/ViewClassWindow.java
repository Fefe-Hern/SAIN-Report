package window.instructor;

import window.admin.*;
import controller.AccountSaver;
import controller.ClassSaver;
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
public class ViewClassWindow {
   static ListView<String> studentList = new ListView<>();
   static ObservableList<String> data;
   static final Label STUDENTNAMELABEL = new Label();
   static Label nameLabel;
   static Label codeNameLabel;
   static Label crnLabel;
   static TextField nameField;
   static TextField codeNameField;
   static TextField crnField;
   static Button gradeClassButton;
   static Button cancelButton;
    
   private static String classCodeName;
   
    public static Scene createScene(String name) {
        classCodeName = name;
        createListView(classCodeName);
        addFields();
        acquireClassInfo();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
        return scene;
    }

    private static void addFields() {
        nameLabel = new Label("Class Name: ");
        codeNameLabel = new Label("Code Name: ");
        crnLabel = new Label("CRN: ");
        nameField = new TextField();
        codeNameField = new TextField();
        crnField = new TextField();
        nameField.setEditable(false);
        codeNameField.setEditable(false);
        crnField.setEditable(false);
        
        gradeClassButton = new Button("Grade Students(DONT)");
        gradeClassButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(GradeStudentsWindow.createScene(classCodeName));
            stage.show();
        });
        
        cancelButton = new Button("Close");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    
    private static void acquireClassInfo() {
        nameField.setText(ClassSaver.passClassToView(classCodeName).getName());
        codeNameField.setText(ClassSaver.passClassToView(classCodeName).getCodeName());
        crnField.setText(ClassSaver.passClassToView(classCodeName).getCrn());
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, nameLabel, codeNameLabel, crnLabel);
        grid.addColumn(1, nameField, codeNameField, crnField, cancelButton);
        grid.addColumn(2, studentList, STUDENTNAMELABEL, gradeClassButton);
        return grid;
    }

    private static void createListView(String codeName) {
        data = FXCollections.observableArrayList();
        ClassSaver.loadStudentsForClass(codeName);
        studentList.setItems(data);
        studentList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            STUDENTNAMELABEL.setText(new_val);
        });
    }

    public static void addStudentToData(String name) {
        data.add(name);
    }
    
    public static void refreshView() {
        createListView(classCodeName);
    }
}
