package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Utils;
import main.controllers.delegates.LoginDelegate;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneLoginController implements Initializable {

    private LoginDelegate loginDelegate;

    @FXML
    public TextField textFieldUsername;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public Button buttonLogin;


    public LoginDelegate getLoginDelegate() { return loginDelegate; }

    public void setLoginDelegate(LoginDelegate loginDelegate) { this.loginDelegate = loginDelegate; }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonLogin.setDisable(true);
    }

    private Boolean validateLogin() {
        // TODO
        // compare with DB data
        return textFieldUsername.getText().equals("user") && textFieldPassword.getText().equals("password"); // JUST A MOCK!!!!
    }

    @FXML
    private void loginButtonWasPressed(ActionEvent actionEvent) {
        if (validateLogin()) {
            loginDelegate.loginEndedSuccessfully();
        } else {
            Utils.showError("I dati non sono corretti");
        }
    }

    @FXML
    private void switchToSigninButtonWasPressed(ActionEvent actionEvent) {
        loginDelegate.shouldSwitchToSignin();
    }

    @FXML
    private void onTextFieldUpdated() {
        if ( textFieldUsername.getText().isEmpty() || textFieldPassword.getText().isEmpty() ) {
            buttonLogin.setDisable(true);
        } else {
            buttonLogin.setDisable(false);
        }
    }
}
