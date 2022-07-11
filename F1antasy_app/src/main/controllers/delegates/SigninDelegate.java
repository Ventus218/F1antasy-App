package main.controllers.delegates;

public interface SigninDelegate {
    void signinEndedSuccessfully();

    void shouldSwitchToLogin();
}
