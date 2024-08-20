package com.eclairview.economiccalendaroverlay.parser;

import com.eclairview.economiccalendaroverlay.model.EconomicEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlParser {

    public ObservableList<EconomicEvent> parseWebView() {
        ObservableList<EconomicEvent> events = FXCollections.observableArrayList();
        try {
            // Example URL, this should be your target URL
            URL url = new URL("https://sslecal2.investing.com?columns=exc_flags,exc_currency,exc_importance,exc_actual,exc_forecast,exc_previous&features=datepicker,timezone&countries=25,32,6,37,72,22,17,39,14,10,35,43,56,36,110,11,26,12,4,5&calType=week&timeZone=23&lang=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Parse the HTML with jsoup
            Document doc = Jsoup.parse(connection.getInputStream(), "UTF-8", "");

            // Find the table rows and extract data
            Elements rows = doc.select("tr");
            for (Element row : rows) {
                String time = row.select("td.time").text();
                String currency = row.select("td.currency").text();
                String event = row.select("td.event").text();
                String actual = row.select("td.actual").text();
                String forecast = row.select("td.forecast").text();
                String previous = row.select("td.previous").text();

                EconomicEvent economicEvent = new EconomicEvent(time, currency, event, actual, forecast, previous);
                events.add(economicEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error parsing HTML: " + e.getMessage());
        }
        return events;
    }
}
