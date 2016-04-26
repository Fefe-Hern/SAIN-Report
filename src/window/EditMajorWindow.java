package window;

import controller.AccountSaver;
import controller.MajorSaver;
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
public class EditMajorWindow {
   static ListView<String> majorList = new ListView<>();
   static ObservableList<String> data;
   static Button addMajorButton;
   static Button cancelButton;
   static final Label MAJORNAMELABEL = new Label();
    
    public static Scene createScene() {
        createListView();
        addFields();
        VBox box = new VBox();
        box.getChildren().addAll(majorList, MAJORNAMELABEL, addMajorButton, cancelButton);
        VBox.setVgrow(majorList, Priority.ALWAYS);
        StackPane root = new StackPane();
        root.getChildren().add(box);
        
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    private static void addFields() {
        addMajorButton = new Button("Add Major");
        addMajorButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(AddMajorWindow.createScene());
            stage.show();
        });
        
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
}
    
    private static void createListView() {
        data = FXCollections.observableArrayList();
        MajorSaver.loadMajorsToData();
        majorList.setItems(data);
        majorList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            MAJORNAMELABEL.setText(new_val);
        });
    }

    public static void refreshListView() {
        createListView();
    }

    public static void addMajorToData(String name) {
        data.add(name);
    }
}
