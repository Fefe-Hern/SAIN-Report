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

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AdminSain extends Application {
    private Button createAccountButton;
    private Button logoutButton;
    
    private TilePane pane;
    
    @Override
    public void start(Stage primaryStage) {
        createButtons(primaryStage);
        createLayout();
        
        StackPane root = new StackPane();
        root.getChildren().add(pane);
        
        Scene scene = new Scene(root, 300, 250);
        
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
        pane.getChildren().add(logoutButton);
        
    }
    
    private void createButtons(Stage primaryStage) {
        createAccountButton = new Button("Create New Account");
        createAccountButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(RegisterAccountWindow.createScene());
            stage.show();
        });
        
        logoutButton = new Button("Logout");
        logoutButton.setOnAction((ActionEvent event) -> {
            new LoginScreen().start(primaryStage);
        });
    }
    
}
