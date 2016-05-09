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
    
    /**
     * Starts up the program via loading all the files (or creating them) and then running the login screen.
     * @param args
     */
    public static void main(String[] args) {
        Serializer.initializeFiles();
        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(window.LoginScreen.class);
            }
        }.start();
    }

    /**
     * Logs into the program provided the information is true.
     * @param userName the username inputted
     * @param password the password inputted
     * @return true if the account is able to be logged in.
     */
    public static boolean login(String userName, String password) {
        Accounts account = AccountController.getAccount(userName, password);
        if(account != null) {
            activeAccount = account;
            return true;
        }
        return false;
    }

    /**
     * Logs out of the activeAccount so as to provide someone else the ability to log in.
     */
    public static void logout() {
        Serializer.saveFiles();
        activeAccount = null;
    }
    
    /**
     * Passes the username of the active account.
     * @return the username of the active account.
     */
    public static String passActiveAccountUserName() {
        return activeAccount.getUserName();
    }

    /**
     * Opens up the specified screen depending on the instance of the account.
     * Admins get access to administrative authorities.
     * Instructors get access to their classes taught.
     * Students get access to register majors and view SAIN report.
     * @param primaryStage
     */
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
