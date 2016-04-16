package window;

import controller.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class LoginScreen extends Application {
    private Label userNameLabel;
    private TextField userTextField;
    private Label passwordLabel;
    private TextField passwordTextField;
    private Button loginButton;
    private Button cancelButton;
    private Button registerButton;
    private FlowPane flow;
    
    @Override
    public void start(Stage primaryStage) {
        createPane();
        setupLabelsAndFields();
        setupButtons(primaryStage);
        addNodesToFlow();
        
        StackPane root = new StackPane();
        root.getChildren().add(flow);
        Scene scene = new Scene(root, 200, 150);

        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            LoginController.logout();
        });
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createPane() {
        flow = new FlowPane();
        flow.setHgap(3);
        flow.setVgap(2);
        flow.setPrefWrapLength(100);
    }

    private void setupLabelsAndFields() {
        userNameLabel = new Label("User Name:");
        passwordLabel = new Label("Password: ");
        userTextField = new TextField();
        passwordTextField = new PasswordField();
    }

    private void setupButtons(Stage primaryStage) {
        loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction((ActionEvent event) -> {
            if (LoginController.login(userTextField.getText(), passwordTextField.getText())) {
                System.out.println("Login Successful");
                LoginController.openSainScreen(primaryStage);
            } else {
                System.out.println("Login Unsuccessful");
            }
        });
        
        registerButton = new Button();
        registerButton.setText("Register");
        registerButton.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        
        cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
    }

    private void addNodesToFlow() {
        flow.getChildren().add(userNameLabel);
        flow.getChildren().add(userTextField);
        flow.getChildren().add(passwordLabel);
        flow.getChildren().add(passwordTextField);
        flow.getChildren().addAll(loginButton, registerButton, cancelButton);
    }
    
}
