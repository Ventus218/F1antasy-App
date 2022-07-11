package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controllers.PaneSigninController;
import main.controllers.TabPaneController;
import main.controllers.delegates.LoginDelegate;
import main.controllers.PaneLoginController;
import main.controllers.delegates.SigninDelegate;

public class Main extends Application implements LoginDelegate, SigninDelegate {

    private Stage primaryStage;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("asset/app_icon.png")));

        showLogin();
        primaryStage.show();
    }

    private void showLogin() {
        FXMLResource resource = loadResource("views/PaneLogin.fxml");
        Parent root = resource.getParent();
        PaneLoginController loginController = (PaneLoginController) resource.getController();
        loginController.setLoginDelegate(this);

        primaryStage.setTitle("F1antasy - Accesso");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
    }

    private void showSignin() {
        FXMLResource resource = loadResource("views/PaneSignin.fxml");
        Parent root = resource.getParent();
        PaneSigninController signinController = (PaneSigninController) resource.getController();
        signinController.setSigninDelegate(this);

        primaryStage.setTitle("F1antasy - Registrazione");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
    }

    private void showApp() {
        FXMLResource resource = loadResource("views/main.fxml");
        Parent root = resource.getParent();
        TabPaneController tabPaneController = (TabPaneController) resource.getController();

        primaryStage.setTitle("F1antasy");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    @Override
    public void loginEndedSuccessfully() {
        showApp();
    }

    @Override
    public void shouldSwitchToSignin() {
        showSignin();
    }

    @Override
    public void signinEndedSuccessfully() {
        showLogin();
    }

    @Override
    public void shouldSwitchToLogin() {
        showLogin();
    }


    private FXMLResource loadResource(String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
        try {
            Parent parent = (Parent) loader.load();
            Object controller = loader.getController();
            return new FXMLResource(parent, controller);
        } catch (Exception e) {
            System.err.println("Si è verificato un errore caricando il file " + name);
            Platform.exit();
        }
        return null; // should never run.
    }
}
