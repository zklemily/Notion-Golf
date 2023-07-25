package com.zkl.notionpageservice.notion.service;

import com.zkl.notionpageservice.dto.PlayerScore;
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

    public String createHeaderBlockJsonPayload(String content) throws JSONException {
        JSONObject requestBody = new JSONObject();
        JSONObject heading = new JSONObject();
        JSONObject text = new JSONObject().put("content", content);
        JSONObject richTextElement = new JSONObject()
                .put("type", "text")
                .put("text", text);
        heading.put("rich_text", new JSONArray(Collections.singletonList(richTextElement)));

        requestBody.put("heading_3", heading);

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

        JSONObject properties = new JSONObject()
                .put("Name", titleObj)
                .put("Venue", venue)
                .put("City", city)
                .put("State", state)
                .put("Country", country)
                .put("Date", date)
                .put("Par", par)
                .put("Yards", yards);

        JSONObject requestBody = new JSONObject().put("parent", parent);
        requestBody.put("properties", properties);

        return requestBody.toString();

    }

    public String createPlayerScoreJsonPayload(String parentPageId) throws JSONException {
        JSONObject parent = new JSONObject()
                .put("type", "page_id")
                .put("page_id", parentPageId);

        JSONArray title = new JSONArray();
        JSONObject titleContent = new JSONObject()
                .put("type", "text")
                .put("text", new JSONObject().put("content", "Leaderboard"));
        title.put(titleContent);

        JSONObject name = new JSONObject()
                .put("type", "title")
                .put("name", "Name")
                .put("title", new JSONObject());

        JSONObject playerId = new JSONObject()
                .put("rich_text", new JSONObject());

        JSONObject country = new JSONObject()
                .put("select", new JSONObject());

        JSONObject totalScore = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject totalStrokes = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject pars = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject birdies = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject eagles = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject bogeys = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject teeTime = new JSONObject()
                .put("date", new JSONObject());

        JSONObject rank = new JSONObject()
                .put("number", new JSONObject().put("format", "number"));

        JSONObject properties = new JSONObject()
                .put("Name", name)
                .put("Rank", rank)
                .put("Country", country)
                .put("Tee Time", teeTime)
                .put("Total Score", totalScore)
                .put("Total Strokes", totalStrokes)
                .put("Eagles", eagles)
                .put("Birdies", birdies)
                .put("Pars", pars)
                .put("Bogeys", bogeys)
                .put("Player ID", playerId);

        JSONObject requestBody = new JSONObject().put("parent", parent)
                .put("title", title)
                .put("properties", properties)
                .put("is_inline", true);

        System.out.println(requestBody);

        return requestBody.toString();
    }

    public String insertPlayerScoreJsonPayload(String databaseId, PlayerScore playerScore) throws JSONException {
        JSONObject parent = new JSONObject()
                .put("database_id", databaseId);

        JSONObject name = new JSONObject()
                .put("title", new JSONArray().put(new JSONObject().put("text", new JSONObject().put("content", playerScore.getName()))));

        JSONObject playerId = new JSONObject()
                .put("rich_text", new JSONArray().put(new JSONObject().put("text", new JSONObject().put("content", playerScore.getId()))));

        JSONObject country = new JSONObject()
                .put("select", new JSONObject().put("name", playerScore.getCountry()));

        JSONObject totalScore = new JSONObject()
                .put("number", playerScore.getScore());

        JSONObject totalStrokes = new JSONObject()
                .put("number", playerScore.getStrokes());

        JSONObject pars = new JSONObject()
                .put("number", playerScore.getPars());

        JSONObject birdies = new JSONObject()
                .put("number", playerScore.getBirdies());

        JSONObject eagles = new JSONObject()
                .put("number", playerScore.getEagles());

        JSONObject bogeys = new JSONObject()
                .put("number", playerScore.getBogeys());

        JSONObject teeTime = new JSONObject()
                .put("date", new JSONObject().put("start", playerScore.getTeeTime()));

        JSONObject rank = new JSONObject()
                .put("number", playerScore.getRank());

        JSONObject properties = new JSONObject()
                .put("Name", name)
                .put("Rank", rank)
                .put("Country", country)
                .put("Tee Time", teeTime)
                .put("Total Score", totalScore)
                .put("Total Strokes", totalStrokes)
                .put("Eagles", eagles)
                .put("Birdies", birdies)
                .put("Pars", pars)
                .put("Bogeys", bogeys)
                .put("Player ID", playerId);

        JSONObject requestBody = new JSONObject().put("parent", parent);
        requestBody.put("properties", properties);

        return requestBody.toString();
    }

    public String updatePlayerScoreJsonPayload(PlayerScore playerScore) throws JSONException {
        JSONObject totalScore = new JSONObject()
                .put("number", playerScore.getScore());

        JSONObject totalStrokes = new JSONObject()
                .put("number", playerScore.getStrokes());

        JSONObject pars = new JSONObject()
                .put("number", playerScore.getPars());

        JSONObject birdies = new JSONObject()
                .put("number", playerScore.getBirdies());

        JSONObject eagles = new JSONObject()
                .put("number", playerScore.getEagles());

        JSONObject bogeys = new JSONObject()
                .put("number", playerScore.getBogeys());

        JSONObject teeTime = new JSONObject()
                .put("date", new JSONObject().put("start", playerScore.getTeeTime()));

        JSONObject rank = new JSONObject()
                .put("number", playerScore.getRank());

        JSONObject properties = new JSONObject()
                .put("Rank", rank)
                .put("Tee Time", teeTime)
                .put("Total Score", totalScore)
                .put("Total Strokes", totalStrokes)
                .put("Eagles", eagles)
                .put("Birdies", birdies)
                .put("Pars", pars)
                .put("Bogeys", bogeys);

        JSONObject requestBody = new JSONObject().put("properties", properties);

        return requestBody.toString();
    }
}