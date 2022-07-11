package main;

import javafx.scene.control.Alert;

public class Utils {

    private Utils() { };

    public static void showError(String title, String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    public static void showError(String message) {
        Utils.showError("Errore", message);
    }
}
