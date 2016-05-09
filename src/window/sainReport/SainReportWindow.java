package window.sainReport;

import controller.AccountController;
import controller.MajorController;
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
    static Label electivesNeededLabel;
    
    static Button viewCoursesForElectiveButton;
    static Button generateWhatIfButton;
    
    static ListView<String> classesTakenList = new ListView<>();
    static ListView<String> currentlyTakingList = new ListView<>();
    static ListView<String> electivesNeededList = new ListView<>();
    static ObservableList<String> dataOfClassesTaken;
    static ObservableList<String> dataOfCurrentlyTaking;
    static ObservableList<String> dataOfElectivesNeeded;
    
    /**
     *
     * @param accountName
     * @param major
     * @return
     */
    public static Scene createScene(String accountName, String major) {
        userAccountName = accountName;
        majorName = major;
        
        addFields();
        createListView();
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

        fullNameLabel.setText("Name: " + AccountController.passStudent(userAccountName).getFullName());
        majorNameLabel.setText("Major: " + MajorController.passMajorToView(majorName).getName());

        classesTakenLabel = new Label("Courses Taken:");
        currentlyTakingLabel = new Label("Courses Currently Taking:");
        electivesNeededLabel = new Label("Courses Needed:");

        viewCoursesForElectiveButton = new Button("View Elective Courses");
        viewCoursesForElectiveButton.setOnAction((ActionEvent event) -> {
            try {
                String elective = electivesNeededList.getSelectionModel().getSelectedItem();
                char[] chars = elective.toCharArray();
                int index1 = 0;
                int index2 = 0;
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '(') {
                        index1 = i + 1;
                    }
                    if (chars[i] == ')') {
                        index2 = i;
                    }
                }
                String electiveCodeName = elective.substring(index1, index2);
                Stage stage = new Stage();
                stage.setScene(SainViewElectiveWindow.createScene(electiveCodeName));
                stage.show();
            } catch (Exception e) {
                //donothing
            }
        });

        generateWhatIfButton = new Button("Generate What-If");
        generateWhatIfButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(SainViewMajorsWindow.createScene(userAccountName));
            stage.show();
        });
    }

    private static void createListView() {
        dataOfClassesTaken = FXCollections.observableArrayList();
        AccountController.loadClassesTaken(userAccountName);
        classesTakenList.setItems(dataOfClassesTaken);
        
        dataOfCurrentlyTaking = FXCollections.observableArrayList();
        AccountController.loadClassesCurrentlyTaking(userAccountName);
        currentlyTakingList.setItems(dataOfCurrentlyTaking);
        
        dataOfElectivesNeeded = FXCollections.observableArrayList();
        MajorController.loadElectivesForSain(majorName);
        electivesNeededList.setItems(dataOfElectivesNeeded);
    }
    
    private static GridPane createLayout() {
        GridPane grid = new GridPane();
        grid.addColumn(0, fullNameLabel, majorNameLabel, gpaLabel, classesTakenLabel, classesTakenList);
        grid.addColumn(1, currentlyTakingLabel, currentlyTakingList);
        grid.addColumn(2, electivesNeededLabel, electivesNeededList, viewCoursesForElectiveButton, generateWhatIfButton);
        return grid;
    }

    /**
     *
     * @param nameAndCode
     */
    public static void addClassToClassesTaken(String nameAndCode) {
        dataOfClassesTaken.add(nameAndCode);
    }
    
    /**
     *
     * @param nameAndCode
     */
    public static void addClassToCurrentlyTaking(String nameAndCode) {
        dataOfCurrentlyTaking.add(nameAndCode);
    }

    /**
     *
     * @param electiveReq
     */
    public static void addElectiveToCoursesNeeded(String electiveReq) {
        dataOfElectivesNeeded.add(electiveReq);
    }

}
