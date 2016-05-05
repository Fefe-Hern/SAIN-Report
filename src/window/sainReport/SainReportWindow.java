package window.sainReport;

import controller.AccountSaver;
import controller.MajorSaver;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class SainReportWindow {
    
    static String userAccountName;
    static String majorName;
    
    static Label fullNameLabel;
    static Label majorNameLabel;
    static Label gpaLabel;
    static Label classesTakenLabel;
    static Label currentlyTakingLabel;
    static Label coursesNeededLabel;
    
    static ListView<String> classesTakenList = new ListView<>();
    static ListView<String> currentlyTakingList = new ListView<>();
    static ObservableList<String> dataOfClassesTaken;
    static ObservableList<String> dataOfCurrentlyTaking;
    
    
    
    public static Scene createScene(String accountName, String major) {
        userAccountName = accountName;
        majorName = major;
        
        addFields();
        createListView();
        //acquireClassInfo();
        GridPane grid = createLayout();
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 700, 250);
        return scene;
    }

    private static void addFields() {
        fullNameLabel = new Label();
        majorNameLabel = new Label();
        gpaLabel = new Label();
        fullNameLabel.setText("Name: " + AccountSaver.passStudent(userAccountName).getFullName());
        majorNameLabel.setText("Major: " + MajorSaver.passMajorToView(majorName).getName());
        
        classesTakenLabel = new Label("Courses Taken:");
        currentlyTakingLabel = new Label("Courses Currently Taking:");
        coursesNeededLabel = new Label("Courses Needed:");
    }

    private static void createListView() {
        dataOfClassesTaken = FXCollections.observableArrayList();
        AccountSaver.loadClassesTaken(userAccountName);
        classesTakenList.setItems(dataOfClassesTaken);
        dataOfCurrentlyTaking = FXCollections.observableArrayList();
        currentlyTakingList.setItems(dataOfCurrentlyTaking);
        AccountSaver.loadClassesCurrentlyTaking(userAccountName);
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, fullNameLabel, majorNameLabel, gpaLabel, classesTakenLabel, classesTakenList);
        grid.addColumn(1, currentlyTakingLabel, currentlyTakingList);
        return grid;
    }

    public static void addClassToClassesTaken(String nameAndCode) {
        dataOfClassesTaken.add(nameAndCode);
    }
    
    public static void addClassToCurrentlyTaking(String nameAndCode) {
        dataOfCurrentlyTaking.add(nameAndCode);
    }

}
