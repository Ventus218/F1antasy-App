package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controllers.*;
import main.controllers.delegates.InizializzazioneSquadraDelegate;
import main.controllers.delegates.LoginDelegate;
import main.controllers.delegates.SigninDelegate;

import java.sql.SQLException;

public class Main extends Application implements LoginDelegate, SigninDelegate, InizializzazioneSquadraDelegate {

    private Stage primaryStage;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("asset/app_icon.png")));

        setupDBAccessAndWait();
        showLogin();
        primaryStage.show();
    }

    private void setupDBAccessAndWait() {
        FXMLResource resource = Utils.loadResource("views/PaneLoginDB.fxml");
        Parent root = resource.getParent();

        Stage s = Utils.getNewStage();
        s.setTitle("F1antasy - Accesso al DB");
        s.setScene(new Scene(root));
        s.setResizable(false);
        s.showAndWait();
    }

    private void showLogin() {
        FXMLResource resource = Utils.loadResource("views/PaneLogin.fxml");
        Parent root = resource.getParent();
        PaneLoginController loginController = (PaneLoginController) resource.getController();
        loginController.setLoginDelegate(this);

        primaryStage.setTitle("F1antasy - Accesso");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
    }

    private void showSignin() {
        FXMLResource resource = Utils.loadResource("views/PaneSignin.fxml");
        Parent root = resource.getParent();
        PaneSigninController signinController = (PaneSigninController) resource.getController();
        signinController.setSigninDelegate(this);

        primaryStage.setTitle("F1antasy - Registrazione");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
    }

    private void showApp() {
        FXMLResource resource = Utils.loadResource("views/TabPane.fxml");
        Parent root = resource.getParent();
        TabPaneController tabPaneController = (TabPaneController) resource.getController();
        tabPaneController.setup();

        primaryStage.setTitle("F1antasy");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    private void showSquadraInitializer() {
        FXMLResource resource = Utils.loadResource("views/PaneInizializzazioneSquadra.fxml");
        Parent root = resource.getParent();
        PaneInizializzazioneSquadraController controller = (PaneInizializzazioneSquadraController) resource.getController();
        controller.setDelegate(this);
        controller.setup();

        primaryStage.setTitle("F1antasy - Inizializzazione Squadra");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    private Boolean userHasAlreadyInitializedSquadra() {
        try {
            return F1antasyDB.getDB().utenteHasInitializedSquadra(User.loggedInUser.getUsername());
        } catch (SQLException e) {
            Utils.crashWithMessage(e.toString());
            return null; // will never run
        }
    }

    @Override
    public void loginEndedSuccessfully(String username) {
        User.loggedInUser.hasLoggedIn(username);

        if (userHasAlreadyInitializedSquadra()) {
            showApp();
        } else {
            showSquadraInitializer();
        }
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

    @Override
    public void squadraWasSuccessfullyInitialized() {
        showApp();
    }
}
