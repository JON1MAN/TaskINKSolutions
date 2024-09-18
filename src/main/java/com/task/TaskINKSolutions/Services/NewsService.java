package com.task.TaskINKSolutions.Services;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Entities.State;
import com.task.TaskINKSolutions.Exceptions.NewsNotFoundException;
import com.task.TaskINKSolutions.Repositories.NewsRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private StateService stateService;
    @Autowired
    private CityService cityService;

    public News getNewsById(Long id){
        return newsRepository.findNewsById(id)
                .orElseThrow(() -> new NewsNotFoundException(
                        "News not found with id: " + id));
    }

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public News createNewsFromGPTResponse(String[] fields){
        String author = fields[0];
        String title = fields[1];
        String description = fields[2];
        String url = fields[3];
        String date = fields[4].substring(0,10);
        String typeOfNews = fields[5].toUpperCase();
        String cityName = fields[6].toUpperCase();
        String stateName = fields[7].toUpperCase();

        News news = new News();
        news.setAuthor(author);
        news.setTitle(title);
        news.setDescription(description);
        news.setUrl(url);
        news.setDate(LocalDate.parse(date));

        if(typeOfNews.equals("GLOBAL")){
            State state = stateService.findStateByName("GLOBAL");
            City city = cityService.findCityByName("GLOBAL");
            news.setCity(city);
            news.setState(state);
            state.addNews(news);

            return createNews(news);
        } else {
            City city = cityService.findCityByNameAndState(cityName, stateName);
            State state = stateService.findStateByName(stateName);
            news.setState(state);
            news.setCity(city);
            createNews(news);

            city.addNews(news);
            state.addNews(news);

            return news;
        }
    }

    public JSONArray fetchNewsForAllStates(){
        List<State> states = stateService.getAllStates().stream()
                .skip(5)
                .toList();

        final String NEWS_API_KEY = "29da19b98724437ebbacc2833282387f";
        RestTemplate restTemplate = new RestTemplate();
        JSONArray usnews = new JSONArray();

        for(State state : states){
            String stateName = state.getName();
            if(stateName.equals("GLOBAL")){
                break;
            }
            String URL = String.format("https://newsapi.org/v2/everything?q=%s CITY&apiKey=%s",
                    stateName,
                    NEWS_API_KEY);
            ResponseEntity<String> response =restTemplate
                    .exchange(URL, HttpMethod.GET, null, String.class);

            if(response.getStatusCode() == HttpStatus.OK){
                JSONObject jsonResponse = new JSONObject(response.getBody());
                JSONArray jsonArray = jsonResponse.getJSONArray("articles");
                usnews.putAll(jsonArray);
            }

        }

        File file = new File("src/main/resources/static/usnews3.json");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(usnews.toString());
        } catch (IOException E){
            E.printStackTrace();
        }

        return usnews;

    }

}
