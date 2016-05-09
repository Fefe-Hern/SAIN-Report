package window;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import window.LoginScreen;
import window.admin.CourseWindow;
import window.admin.ElectiveWindow;
import window.admin.MajorWindow;
import window.admin.RegisterAccountWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AdminSain extends Application {
    private Button createAccountButton;
    private Button logoutButton;
    private Button editMajorsButton;
    private Button editElectivesButton;
    private Button editCoursesButton;
    
    private TilePane pane;
    
    @Override
    public void start(Stage primaryStage) {
        createButtons(primaryStage);
        createLayout();
        
        StackPane root = new StackPane();
        root.getChildren().add(pane);
        
        Scene scene = new Scene(root, 150, 200);
        
        primaryStage.setTitle("Admin Pane");
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createLayout() {
        pane = new TilePane(5, 5);
        pane.setPrefColumns(2);
        pane.getChildren().add(createAccountButton);
        pane.getChildren().add(editMajorsButton);
        pane.getChildren().add(editElectivesButton);
        pane.getChildren().add(editCoursesButton);
        pane.getChildren().add(logoutButton);
        
    }
    
    private void createButtons(Stage primaryStage) {
        createAccountButton = new Button("Create New Account");
        createAccountButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(RegisterAccountWindow.createScene());
            stage.show();
        });
        
        editMajorsButton = new Button("Edit Majors");
        editMajorsButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(MajorWindow.createScene());
            stage.show();
        });
        
        editElectivesButton = new Button("Edit Electives");
        editElectivesButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(ElectiveWindow.createScene());
            stage.show();
        });
        
        editCoursesButton = new Button("Edit Courses");
        editCoursesButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(CourseWindow.createScene());
            stage.show();
        });
        
        logoutButton = new Button("Logout");
        logoutButton.setOnAction((ActionEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
    }
    
}
