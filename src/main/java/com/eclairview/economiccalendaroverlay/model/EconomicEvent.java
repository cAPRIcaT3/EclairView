package com.eclairview.economiccalendaroverlay.model;

public class EconomicEvent {
    private String time;
    private String currency;
    private String event;
    private String actual;
    private String forecast;
    private String previous;

    public EconomicEvent(String time, String currency, String event, String actual, String forecast, String previous) {
        this.time = time;
        this.currency = currency;
        this.event = event;
        this.actual = actual;
        this.forecast = forecast;
        this.previous = previous;
    }

    public String getTime() {
        return time;
    }

    public String getCurrency() {
        return currency;
    }

    public String getEvent() {
        return event;
    }

    public String getActual() {
        return actual;
    }

    public String getForecast() {
        return forecast;
    }

    public String getPrevious() {
        return previous;
    }
}
