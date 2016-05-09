package window.admin;

import controller.AccountController;
import controller.ElectiveController;
import controller.MajorController;
import controller.Serializer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class ElectiveWindow {
   static ListView<String> electiveList = new ListView<>();
   static ObservableList<String> data;
   static Button addElectiveButton;
   static Button deleteElectiveButton;
   static Button electivePropertiesButton;
   static Button cancelButton;
   static final Label ELECTIVENAMELABEL = new Label();
    
    /**
     *
     * @return
     */
    public static Scene createScene() {
        createListView();
        addFields();
        VBox box = new VBox();
        box.getChildren().addAll(electiveList, ELECTIVENAMELABEL, addElectiveButton, electivePropertiesButton,
                deleteElectiveButton, cancelButton);
        VBox.setVgrow(electiveList, Priority.ALWAYS);
        StackPane root = new StackPane();
        root.getChildren().add(box);
        
        Scene scene = new Scene(root, 300, 300);
        return scene;
    }

    private static void addFields() {
        addElectiveButton = new Button("Add Elective");
        addElectiveButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(AddElectiveWindow.createScene());
            stage.show();
        });
        
        electivePropertiesButton = new Button("View Properties");
        electivePropertiesButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(ElectivePropertiesWindow.createScene(electiveList.getSelectionModel().getSelectedItem()));
            stage.show();
        });
        
        deleteElectiveButton = new Button("Delete Selected Elective");
        deleteElectiveButton.setOnAction((ActionEvent event) -> {
            ElectiveController.deleteElective(electiveList.getSelectionModel().getSelectedItem());
        });
        
        cancelButton = new Button("Save & Quit");
        cancelButton.setOnAction((ActionEvent event) -> {
            Serializer.saveFiles();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static void createListView() {
        data = FXCollections.observableArrayList();
        ElectiveController.loadElectivesToData();
        electiveList.setItems(data);
        electiveList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            ELECTIVENAMELABEL.setText(new_val);
        });
    }

    /**
     *
     */
    public static void refreshListView() {
        createListView();
    }

    /**
     *
     * @param name
     */
    public static void addElectiveToData(String name) {
        data.add(name);
    }
}
