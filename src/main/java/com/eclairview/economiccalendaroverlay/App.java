package com.eclairview.economiccalendaroverlay;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        // Set the application icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/app-icon.png")));

        // Create a WebView to display the economic calendar iframe
        WebView webView = new WebView();
        webView.getEngine().load("https://sslecal2.investing.com?columns=exc_flags,exc_currency,exc_importance,exc_actual,exc_forecast,exc_previous&features=datepicker,timezone&countries=25,32,6,37,72,22,17,39,14,10,35,43,56,36,110,11,26,12,4,5&calType=week&timeZone=23&lang=1");

        // Adjust the zoom level of the WebView content
        webView.setZoom(1.2); // Change the zoom level to a value that looks better (default is 1.0)

        // Make the WebView background more translucent
        webView.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);");

        // Create a custom title bar with a close button
        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("close-button"); // Apply CSS class

        closeButton.setOnAction(event -> primaryStage.close());

        // Use an HBox for the title bar
        HBox titleBar = new HBox();
        titleBar.setSpacing(10);
        titleBar.setPadding(new Insets(10));
        titleBar.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2); -fx-text-fill: white;");

        // Position the close button to the right
        HBox spacer = new HBox(); // A spacer to push the close button to the right
        HBox.setHgrow(spacer, Priority.ALWAYS); // Grow the spacer to take up space

        titleBar.getChildren().addAll(spacer, closeButton);

        // Make the window draggable using the custom title bar
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // Create the main layout
        VBox root = new VBox(titleBar, webView);
        Scene scene = new Scene(root, 750, 661, Color.TRANSPARENT); // Set initial dimensions here

        // Apply any external stylesheets if needed
        scene.getStylesheets().add(getClass().getResource("/overlay.css").toExternalForm());

        // Customize the window appearance
        primaryStage.initStyle(StageStyle.TRANSPARENT); // Make the window borderless and transparent
        primaryStage.setAlwaysOnTop(true); // Keep the window on top of others
        primaryStage.setOpacity(0.9); // Set the opacity to make it translucent

        // Set up the stage and show it
        primaryStage.setTitle("Economic Calendar Overlay");
        primaryStage.setScene(scene);
        primaryStage.setWidth(750);
        primaryStage.setHeight(661);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
