package window;

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
import window.instructor.ViewClassesTaughtWindow;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class InstructorSain extends Application {
    private Button viewClassesButton;
    private Button logoutButton;
    
    private TilePane pane;
    
    @Override
    public void start(Stage primaryStage) {
        createButtons(primaryStage);
        createLayout();
        
        StackPane root = new StackPane();
        root.getChildren().add(pane);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Instructor Pane");
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createLayout() {
        pane = new TilePane(5, 5);
        pane.setPrefColumns(2);
        pane.getChildren().add(viewClassesButton);
        pane.getChildren().add(logoutButton);
        
    }
    
    private void createButtons(Stage primaryStage) {
        viewClassesButton = new Button("View Classes Taught");
        viewClassesButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(ViewClassesTaughtWindow.createScene(LoginController.passActiveAccountUserName()));
            stage.show();
        });
        
        logoutButton = new Button("Logout");
        logoutButton.setOnAction((ActionEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
    }
}
