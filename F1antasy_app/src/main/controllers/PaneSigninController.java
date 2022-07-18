package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.F1antasyDB;
import main.Utils;
import main.controllers.delegates.SigninDelegate;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaneSigninController implements Initializable {

    private SigninDelegate signinDelegate;

    @FXML
    public TextField textFieldUsername;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public PasswordField textFieldConfirmPassword;
    @FXML
    public Button buttonSignin;


    public SigninDelegate getSigninDelegate() { return signinDelegate; }

    public void setSigninDelegate(SigninDelegate signinDelegate) { this.signinDelegate = signinDelegate; }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonSignin.setDisable(true);
    }

    private Boolean validateSignin() {
        return textFieldPassword.getText().equals(textFieldConfirmPassword.getText());
    }

    @FXML
    private void signinButtonWasPressed(ActionEvent actionEvent) {
        if (validateSignin()) {
            try {
                F1antasyDB.signInUtente(textFieldUsername.getText(), textFieldPassword.getText());
                signinDelegate.signinEndedSuccessfully();
            }
            catch (SQLException e) {
                Utils.showError("E' possibile che l'username scelto sia già utilizzato.\n Errore: " + e.toString());
            }
        } else {
            Utils.showError("Le password non coincidono.");
        }
    }

    @FXML
    private void switchToLoginButtonWasPressed(ActionEvent actionEvent) {
        signinDelegate.shouldSwitchToLogin();
    }

    @FXML
    private void onTextFieldUpdated() {
        if ( textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty() || textFieldConfirmPassword.getText().isEmpty() ) {
            buttonSignin.setDisable(true);
        } else {
            buttonSignin.setDisable(false);
        }
    }
}
