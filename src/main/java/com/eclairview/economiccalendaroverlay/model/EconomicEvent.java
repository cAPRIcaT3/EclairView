package com.eclairview.economiccalendaroverlay.model;

import javafx.beans.property.SimpleStringProperty;

public class EconomicEvent {
    private final SimpleStringProperty time;
    private final SimpleStringProperty currency;
    private final SimpleStringProperty eventName;
    private final SimpleStringProperty actual;
    private final SimpleStringProperty forecast;
    private final SimpleStringProperty previous;

    public EconomicEvent(String time, String currency, String eventName, String actual, String forecast, String previous) {
        this.time = new SimpleStringProperty(time);
        this.currency = new SimpleStringProperty(currency);
        this.eventName = new SimpleStringProperty(eventName);
        this.actual = new SimpleStringProperty(actual);
        this.forecast = new SimpleStringProperty(forecast);
        this.previous = new SimpleStringProperty(previous);
    }

    public String getTime() { return time.get(); }
    public String getCurrency() { return currency.get(); }
    public String getEventName() { return eventName.get(); }
    public String getActual() { return actual.get(); }
    public String getForecast() { return forecast.get(); }
    public String getPrevious() { return previous.get(); }
}
