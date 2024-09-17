package com.task.TaskINKSolutions;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Services.CityService;
import com.task.TaskINKSolutions.Services.GeolocationService;
import com.task.TaskINKSolutions.Services.NewsService;
import com.task.TaskINKSolutions.Services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getNewsFromCityStateGlobal(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude
    ){
        String cityName = geolocationService.getCityAndState(latitude, longitude);
        City city = cityService.findCityByName(cityName);
        List<News> newsFromCity = city.getNewsFromCity();
        List<News> newsFromState = city.getState().getNewsFromState();
        List<News> globalNews = stateService.findStateByName("GLOBAL").getNewsFromState()
                .stream()
                .limit(2)
                .toList();

        return ResponseEntity.ok(cityName);
    }

}
