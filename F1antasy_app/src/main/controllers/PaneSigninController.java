package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Utils;
import main.controllers.delegates.SigninDelegate;

import java.net.URL;
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
        if ( ! textFieldPassword.getText().equals(textFieldConfirmPassword.getText()) ) {
            return false;
        } else {
            // TODO
            // check DB data
            return true; // JUST A MOCK!!!!
        }
    }

    @FXML
    private void signinButtonWasPressed(ActionEvent actionEvent) {
        if (validateSignin()) {
            signinDelegate.signinEndedSuccessfully();
        } else {
            if ( ! textFieldPassword.getText().equals(textFieldConfirmPassword.getText()) ) {
                Utils.showError("Le password non coincidono.");
            } else {
                Utils.showError("La registrazione non è andata a buon fine.");
            }
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
