package com.task.TaskINKSolutions.Services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Service
public class GptFilterService {
    @Value("${OPENAI_API_KEY}")
    private String KEY;
    private final String OPENAI_URL =  "https://api.openai.com/v1/chat/completions";

    public String textAnalysis(String inputText){
        final String stepsToFilter = """
                \nand give me a response as follows typeOfNews, City_name, State_full_name:
                1) typeOfNews :
                can be LOCAL OR GLOBAL
                LOCAL are news from US cities and states
                GLOBAL - global news
                2) if typeOfNews = LOCAL, determine name of city and state (full name of state and city) for exp not D.C. but DISTRICT OF COLUMBIA
                 3) if typeOfNews = GLOBAL, set city = determine full name of city (if you didn't find out name, set GLOBAL), state = GLOBAL
                
                4) Give response
                Exp.
                LOCAL, MOUNTAIN HOME, IDAHO
                """;
        final String text = "Make analysis of following text:" +
                inputText + stepsToFilter;

        RestTemplate restTemplate = new RestTemplate();

        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + KEY);
        headers.set("Content-Type", "application/json");

        //Payload
        JSONObject requestPayload = new JSONObject();
        requestPayload.put("model", "gpt-4o-mini");

        //Array to store messages
        JSONArray messageArray = new JSONArray();
        JSONObject messageObject = new JSONObject();
        messageObject.put("role", "user");
        messageObject.put("content", text);
        messageArray.put(messageObject);

        requestPayload.put("messages", messageArray);
        requestPayload.put("max_tokens", 10000);

        HttpEntity<String> entity = new HttpEntity<>(requestPayload.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(OPENAI_URL, HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        String generatedText = jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0).toString();

        return generatedText;

    }

}
