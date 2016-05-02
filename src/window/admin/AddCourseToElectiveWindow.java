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

    static Label nameLabel;
    static Label codeNameLabel;
    static Label creditsGivenLabel;
    static Label instructorLabel;
    static TextField nameField;
    static TextField codeNameField;
    static TextField creditsGivenField;
    static TextField instructorField;
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
        instructorLabel = new Label("Instructor's Username: ");
        nameField = new TextField();
        codeNameField = new TextField();
        creditsGivenField = new TextField();
        instructorField = new TextField();

        createButton = new Button("Create Course");
        createButton.setOnAction((ActionEvent event) -> {
            String name = nameField.getText();
            String codeName = codeNameField.getText();
            String creditsGiven = creditsGivenField.getText();
            String instructorName = instructorField.getText();

            if (!name.isEmpty() && !codeName.isEmpty() && !creditsGiven.isEmpty() && !instructorName.isEmpty()) {
                if (ElectiveSaver.addClass(name, codeName,
                        creditsGiven, instructorName)) {
                    Stage stage = (Stage) createButton.getScene().getWindow();
                    stage.close();
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
        grid.addColumn(0, nameLabel, codeNameLabel, createButton);
        grid.addColumn(1, nameField, codeNameField, cancelButton);
        return grid;
    }
}
