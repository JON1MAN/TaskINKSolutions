package com.task.TaskINKSolutions.Services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Service
public class GptFilterService {
    private final String OPENAI_API_KEY = "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";
    private final String OPENAI_URL =  "https://api.openai.com/v1/chat/completions";

    public String textAnalysis(String inputText){
        final String stepsToFilter = """
                \nand give me a response as follows typeOfNews, City_name, State_full_name:
                1) typeOfNews :
                can be LOCAL OR GLOBAL
                LOCAL are news from US cities and states
                GLOBAL - global news
                2) if typeOfNews = LOCAL, determine name of city and state
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
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
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
