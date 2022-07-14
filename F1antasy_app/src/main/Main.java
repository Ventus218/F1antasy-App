package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controllers.PaneInizializzazioneSquadraController;
import main.controllers.PaneSigninController;
import main.controllers.TabPaneController;
import main.controllers.delegates.InizializzazioneSquadraDelegate;
import main.controllers.delegates.LoginDelegate;
import main.controllers.PaneLoginController;
import main.controllers.delegates.SigninDelegate;

import java.util.Optional;

public class Main extends Application implements LoginDelegate, SigninDelegate, InizializzazioneSquadraDelegate {

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
        tabPaneController.initialize();

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
        controller.initialize();

        primaryStage.setTitle("F1antasy - Inizializzazione Squadra");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }

    private boolean userHasAlreadyInitializedSquadra() {
        // TODO CHECK IN DB IF USER HAS A SQUADRA FOR THE CURRENT CAMPIONATO

        // MOCKUP
        return false;
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
