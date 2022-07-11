package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controllers.PaneLoginController;

interface LoginDelegate {
    void onLoginCompleted();
}

public class Main extends Application implements LoginDelegate {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main.fxml"));
        Parent root = (Parent)loader.load();
        primaryStage.setTitle("F1antasy");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("asset/app_icon.png")));
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();

        PaneLoginController controller = (PaneLoginController)loader.getController();

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onLoginCompleted() {

    }
}
