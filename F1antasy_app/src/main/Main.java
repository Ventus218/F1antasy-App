package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/main.fxml"));
        primaryStage.setTitle("F1antasy");
        primaryStage.getIcons().add(new Image("main/asset/app_icon.png"));
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.setMinWidth(1080);
        primaryStage.setMinHeight(720);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
