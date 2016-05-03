package controller;

import dataModel.Accounts;
import dataModel.Admin;
import dataModel.Instructor;
import dataModel.Student;
import javafx.stage.Stage;
import window.AdminSain;
import window.InstructorSain;
import window.StudentSain;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class LoginController {

    private static Accounts activeAccount = null;
    
    public static void main(String[] args) {
        Serializer.initializeFiles();
        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(window.LoginScreen.class);
            }
        }.start();
    }
    public static boolean login(String userName, String password) {
        Accounts account = AccountSaver.getAccount(userName, password);
        if(account != null) {
            activeAccount = account;
            return true;
        }
        return false;
    }

    public static void logout() {
        Serializer.saveFiles();
        activeAccount = null;
    }
    
    public static String passActiveAccountUserName() {
        return activeAccount.getUserName();
    }

    public static void openSainScreen(Stage primaryStage) {
        if(activeAccount instanceof Admin) {
            new AdminSain().start(primaryStage);
        } else if (activeAccount instanceof Instructor) {
            new InstructorSain().start(primaryStage);
        } else if (activeAccount instanceof Student) {
            new StudentSain().start(primaryStage);
        }
    }
}
