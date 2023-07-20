package com.zkl.notionpageservice.notion.service;

import com.zkl.notionpageservice.dto.Tournament;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component
public class JsonService {
    public String createBlockJsonPayload(String content) throws JSONException {
        JSONObject requestBody = new JSONObject();
        JSONObject paragraph = new JSONObject();
        JSONArray richText = new JSONArray();
        JSONObject text = new JSONObject();
        text.put("content", content);
        JSONObject richTextElement = new JSONObject();
        richTextElement.put("type", "text");
        richTextElement.put("text", text);
        paragraph.put("rich_text", new JSONArray(Collections.singletonList(richTextElement)));

        requestBody.put("paragraph", paragraph);

        return requestBody.toString();
    }

    public String createTournamentJsonPayload(String databaseId, Tournament tournament) throws JSONException {
        JSONObject parent = new JSONObject().put("database_id", databaseId);

        JSONArray title = new JSONArray();
        JSONObject text = new JSONObject().put("content", tournament.getName());
        JSONObject titleText = new JSONObject().put("text", text);
        title.put(titleText);
        JSONObject titleObj = new JSONObject().put("title", title);


        JSONObject country = new JSONObject().put("type", "select");
        JSONObject countrySelect = new JSONObject();
        if (tournament.getCountry() == null) {
            countrySelect.put("name", "N/A");
        } else {
            countrySelect.put("name", tournament.getCountry());
        }
        country.put("select", countrySelect);

        JSONObject venue = new JSONObject();
        JSONArray richText = new JSONArray();
        JSONObject venueText = new JSONObject().put("content", tournament.getVenue());
        richText.put(new JSONObject().put("text", venueText));
        venue.put("rich_text", richText);

        JSONObject city = new JSONObject();
        richText = new JSONArray();
        JSONObject cityText = new JSONObject();
        if (tournament.getCity() == null) {
            cityText.put("content", "");
        } else {
            cityText.put("content", tournament.getCity());
        }
        richText.put(new JSONObject().put("text", cityText));
        city.put("rich_text", richText);

        JSONObject stateText = new JSONObject();
        if (tournament.getState() == null) {
            stateText.put("name", "N/A");
        } else {
            stateText.put("name", tournament.getState());
        }
        JSONObject state = new JSONObject().put("select", stateText);

        JSONObject yards = new JSONObject();
        if (tournament.getYards() == null) {
            yards.put("number", 0);
        } else {
            yards.put("number", tournament.getYards());
        }

        JSONObject par = new JSONObject();
        if (tournament.getPar() == null) {
            par.put("number", 0);
        } else {
            par.put("number", tournament.getPar());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        JSONObject dates = new JSONObject().put("start", tournament.getStartDate().format(formatter));
        dates.put("end", tournament.getEndDate().format(formatter));
        JSONObject date = new JSONObject().put("date", dates);

        JSONObject properties = new JSONObject().put("Name", titleObj);
        properties.put("Venue", venue);
        properties.put("City", city);
        properties.put("State", state);
        properties.put("Country", country);
        properties.put("Date", date);
        properties.put("Par", par);
        properties.put("Yards", yards);

        JSONObject requestBody = new JSONObject().put("parent", parent);
        requestBody.put("properties", properties);

        return requestBody.toString();

    }
}
