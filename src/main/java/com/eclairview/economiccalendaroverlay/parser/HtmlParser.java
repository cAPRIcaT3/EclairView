package com.eclairview.economiccalendaroverlay.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public void parse(String html) {
        Document doc = Jsoup.parse(html);

        // Example: Extracting data from table rows
        Elements rows = doc.select("table tr");
        for (Element row : rows) {
            Elements cells = row.select("td");
            for (Element cell : cells) {
                System.out.println(cell.text());
            }
        }
    }
}
