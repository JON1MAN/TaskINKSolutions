package com.task.TaskINKSolutions;

import com.task.TaskINKSolutions.DTOs.NewsResponse;
import com.task.TaskINKSolutions.DTOs.ResponseDTO;
import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Services.CityService;
import com.task.TaskINKSolutions.Services.GeolocationService;
import com.task.TaskINKSolutions.Services.NewsService;
import com.task.TaskINKSolutions.Services.StateService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ap1/v1/news")
public class NewsController {

    @Autowired
    private CityService cityService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private StateService stateService;
    @Autowired
    private GeolocationService geolocationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<ResponseDTO> getNewsFromCityStateGlobal(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude
    ){
        String cityName = geolocationService.getCityAndState(latitude, longitude);
        City city = cityService.findCityByName(cityName);
        List<NewsResponse> newsFromCity = createNewsResponse(city.getNewsFromCity());
        List<NewsResponse> newsFromState = createNewsResponse(city.getState().getNewsFromState());
        List<NewsResponse> globalNews = stateService.findStateByName("GLOBAL").getNewsFromState()
                .stream()
                .map(n -> new NewsResponse(
                        n.getAuthor(),
                        n.getTitle(),
                        n.getDescription(),
                        n.getUrl(),
                        n.getDate()
                ))
                .limit(2)
                .toList();

        ResponseDTO response = new ResponseDTO();
        response.setCityName(cityName);
        response.setStateName(city.getState().getName());
        response.setCityNews(newsFromCity);
        response.setStateNews(newsFromState);
        response.setGlobalNews(globalNews);

        return ResponseEntity.ok(response);
    }

    public List<NewsResponse> createNewsResponse(List<News> news){
        List<NewsResponse> response = news.stream()
                .map(n -> new NewsResponse(
                        n.getAuthor(),
                        n.getTitle(),
                        n.getDescription(),
                        n.getUrl(),
                        n.getDate()
                ))
                .toList();
        return response;
    }

}
