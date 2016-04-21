package window;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class AdminSain extends Application {
    private Button createAccountButton;
    
    @Override
    public void start(Stage primaryStage) {
        createButtons();
        
        StackPane root = new StackPane();
        root.getChildren().add(createAccountButton);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createButtons() {
        createAccountButton = new Button();
        createAccountButton.setText("Create New Account");
        createAccountButton.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            stage.setScene(RegisterAccountWindow.createScene());
            stage.show();
        });
    }
    
}
