package controller;

/**
 *
 * @author Fefe-Hern <https://github.com/Fefe-Hern>
 */
public class RunLoginScreen {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(window.LoginScreen.class);
            }
        }.start();
    }
}
