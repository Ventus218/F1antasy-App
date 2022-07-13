package main.controllers.delegates;

public interface LoginDelegate {
    void loginEndedSuccessfully(String username);

    void shouldSwitchToSignin();
}
