package com.eclairview.economiccalendaroverlay.controller;

import com.eclairview.economiccalendaroverlay.model.EconomicEvent;
import com.eclairview.economiccalendaroverlay.parser.HtmlParser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OverlayController {

    @FXML
    private HBox titleBar;

    @FXML
    private Button closeButton;

    @FXML
    private TableView<EconomicEvent> tableView;

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

        setupTableView();

        // Parse data and populate the table
        HtmlParser parser = new HtmlParser();
        ObservableList<EconomicEvent> events = parser.parseWebView();

        tableView.setItems(events);
    }

    private void setupTableView() {
        TableColumn<EconomicEvent, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<EconomicEvent, String> currencyColumn = new TableColumn<>("Currency");
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

        TableColumn<EconomicEvent, String> eventColumn = new TableColumn<>("Event");
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));

        TableColumn<EconomicEvent, String> actualColumn = new TableColumn<>("Actual");
        actualColumn.setCellValueFactory(new PropertyValueFactory<>("actual"));

        TableColumn<EconomicEvent, String> forecastColumn = new TableColumn<>("Forecast");
        forecastColumn.setCellValueFactory(new PropertyValueFactory<>("forecast"));

        TableColumn<EconomicEvent, String> previousColumn = new TableColumn<>("Previous");
        previousColumn.setCellValueFactory(new PropertyValueFactory<>("previous"));

        // Clear existing columns and add new ones to avoid duplication
        tableView.getColumns().clear();
        tableView.getColumns().addAll(timeColumn, currencyColumn, eventColumn, actualColumn, forecastColumn, previousColumn);
    }
}
