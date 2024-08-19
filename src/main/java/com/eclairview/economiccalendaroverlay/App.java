package com.eclairview.economiccalendaroverlay;

import com.eclairview.economiccalendaroverlay.controller.OverlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OverlayUI.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the controller and pass the stage to it
            OverlayController controller = loader.getController();
            controller.setStage(primaryStage);

            // Set the application icon
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/app-icon.png")));

            // Set up the stage
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.setOpacity(0.9);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}