package window;

import controller.AccountController;
import controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import window.sainReport.SainReportWindow;
import window.student.RegisterClassesWindow;
import window.student.RegisterMajorWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class StudentSain extends Application {
    private Button registerClassButton;
    private Button registerMajorButton;
    private Button viewSainButton;
    private Button logoutButton;
    
    private TilePane pane;
    
    @Override
    public void start(Stage primaryStage) {
        createButtons(primaryStage);
        createLayout();
        
        StackPane root = new StackPane();
        root.getChildren().add(pane);
        
        Scene scene = new Scene(root, 150, 200);
        
        primaryStage.setTitle("Student Pane");
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createLayout() {
        pane = new TilePane(5, 5);
        pane.setPrefColumns(2);
        pane.getChildren().add(registerMajorButton);
        pane.getChildren().add(registerClassButton);
        pane.getChildren().add(viewSainButton);
        pane.getChildren().add(logoutButton);
        
    }
    
    private void createButtons(Stage primaryStage) {
        registerClassButton = new Button("Register for Classes");
        registerClassButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(RegisterClassesWindow.createScene(LoginController.passActiveAccountUserName()));
            stage.show();
        });
        
        registerMajorButton = new Button("Register for Major");
        registerMajorButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(RegisterMajorWindow.createScene(LoginController.passActiveAccountUserName()));
            stage.show();
        });
        
        viewSainButton = new Button("View SAIN Report");
        viewSainButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            String studentName = LoginController.passActiveAccountUserName();
            stage.setScene(SainReportWindow.createScene(studentName,
                    AccountController.passStudent(studentName).getMajor().getCodeName()));
            stage.show();
        });
        
        logoutButton = new Button("Logout");
        logoutButton.setOnAction((ActionEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
    }
}
