package window.sainReport;

import window.admin.*;
import controller.AccountController;
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
public class SainViewMajorsWindow {
   static ListView<String> majorList = new ListView<>();
   static ObservableList<String> data;
   static Button selectMajorButton;
   static Button cancelButton;
   static final Label MAJORNAMELABEL = new Label();
   
   static String userAccountName;
    
    /**
     *
     * @param userName
     * @return
     */
    public static Scene createScene(String userName) {
        userAccountName = userName;
        createListView();
        addFields();
        VBox box = new VBox();
        box.getChildren().addAll(majorList, MAJORNAMELABEL, selectMajorButton, cancelButton);
        VBox.setVgrow(majorList, Priority.ALWAYS);
        StackPane root = new StackPane();
        root.getChildren().add(box);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        selectMajorButton = new Button("Select Major");
        selectMajorButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(SainReportWindow.createScene(userAccountName, majorList.getSelectionModel().getSelectedItem()));
            stage.show();
        });
        
        cancelButton = new Button("Save and Quit");
        cancelButton.setOnAction((ActionEvent event) -> {
            Serializer.saveFiles();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static void createListView() {
        data = FXCollections.observableArrayList();
        MajorController.loadMajorsToData("SAIN");
        majorList.setItems(data);
        majorList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            MAJORNAMELABEL.setText(new_val);
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
    public static void addMajorToData(String name) {
        data.add(name);
    }
}
