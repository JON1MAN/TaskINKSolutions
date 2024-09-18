package com.task.TaskINKSolutions.Services;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class GeolocationService {

    private final String GOOGLE_API_KEY = "AIzaSyAyKABWechfSlzZEg4GF4yQ5Rob7QkYiZ8";

    public String getCityAndState(String latitude, String longitude){
        RestTemplate restTemplate = new RestTemplate();
        final String GEOLOCATION_API_URL =
                String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s",
                        latitude, longitude, GOOGLE_API_KEY);

        ResponseEntity<String> response = restTemplate
                .exchange(GEOLOCATION_API_URL, HttpMethod.GET, null, String.class);

        JSONObject responseJSON = new JSONObject(response.getBody())
                .getJSONObject("plus_code");
        String compound_code = responseJSON.getString("compound_code").trim();
        String[] address = compound_code.split(", ");
        String city = address[0].substring(compound_code.indexOf(' ') + 1);
        return city.toUpperCase();
    }
}
