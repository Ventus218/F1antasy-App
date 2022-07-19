package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.F1antasyDB;
import main.Utils;
import main.controllers.delegates.LoginDBDelegate;
import main.controllers.delegates.LoginDelegate;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaneLoginDBController implements Initializable {

    private LoginDBDelegate loginDelegate;

    @FXML
    public TextField textFieldUsername;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public Button buttonLogin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonLogin.setDisable(true);
    }

    public LoginDBDelegate getLoginDelegate() { return loginDelegate; }

    public void setLoginDelegate(LoginDBDelegate loginDelegate) { this.loginDelegate = loginDelegate; }

    @FXML
    private void loginButtonWasPressed(ActionEvent actionEvent) {
        try {
            F1antasyDB.setUpDB(textFieldUsername.getText(), textFieldPassword.getText());
            loginDelegate.loginDBEndedSuccessfully();
            ((Stage) buttonLogin.getScene().getWindow()).close();
        } catch (Exception e) {
            Utils.showError("Qualcosa è andato storto, assicurati che i dati siano corretti e riprova.");
            textFieldUsername.setText("");
            textFieldPassword.setText("");
            buttonLogin.setDisable(true);
        }
    }

    @FXML
    private void onTextFieldUpdated() {
        if ( textFieldUsername.getText().isEmpty() ) {
            buttonLogin.setDisable(true);
        } else {
            buttonLogin.setDisable(false);
        }
    }
}
