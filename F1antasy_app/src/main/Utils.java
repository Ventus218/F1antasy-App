package main;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static FXMLResource loadResource(String name) {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(name));
        try {
            Parent parent = (Parent) loader.load();
            Object controller = loader.getController();
            return new FXMLResource(parent, controller);
        } catch (Exception e) {
            Utils.crashWithMessage("Si è verificato un errore caricando il file " + name);
            return null; // will never run
        }
    }

    public static Stage getNewStage() {
        Stage s = new Stage();
        s.setTitle("F1antasy");
        s.getIcons().add(new Image(Utils.class.getResourceAsStream("asset/app_icon.png")));
        return s;
    }

    public static void crashWithMessage(String message) {
        Platform.exit();
        System.err.println(message);
        System.exit(1);
    }

    public static Date dateFromStringDDMMYYYY(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            Utils.crashWithMessage("ParseException trying to parse: " + dateString);
            return null; // will never run
        }
    }
}
