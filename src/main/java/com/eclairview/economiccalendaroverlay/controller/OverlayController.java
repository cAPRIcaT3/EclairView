package com.eclairview.economiccalendaroverlay.controller;

import com.eclairview.economiccalendaroverlay.model.EconomicEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class OverlayController {

    @FXML
    private HBox titleBar;

    @FXML
    private Button closeButton;

    @FXML
    private WebView webView;

    @FXML
    private TableView<EconomicEvent> tableView;

    @FXML
    private TableColumn<EconomicEvent, String> timeColumn;
    @FXML
    private TableColumn<EconomicEvent, String> currencyColumn;
    @FXML
    private TableColumn<EconomicEvent, String> eventColumn;
    @FXML
    private TableColumn<EconomicEvent, String> actualColumn;
    @FXML
    private TableColumn<EconomicEvent, String> forecastColumn;
    @FXML
    private TableColumn<EconomicEvent, String> previousColumn;

    private Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        closeButton.setOnAction(event -> {
            if (stage != null) {
                stage.close();
            }
        });

        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            if (stage != null) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        // Set up the table columns
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        actualColumn.setCellValueFactory(new PropertyValueFactory<>("actual"));
        forecastColumn.setCellValueFactory(new PropertyValueFactory<>("forecast"));
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("previous"));

        // Add some sample data to the table
        ObservableList<EconomicEvent> data = FXCollections.observableArrayList(
                new EconomicEvent("04:00", "NZD", "Performance of Services Index", "44.6", "40.7", ""),
                new EconomicEvent("04:31", "GBP", "Rightmove House Price Index (MoM)", "-1.5%", "-0.4%", "")
        );

        tableView.setItems(data);
    }
}
