package com.task.TaskINKSolutions.Services;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Exceptions.CityNotFoundException;
import com.task.TaskINKSolutions.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findCityById(Long id){
        return cityRepository.findCityById(id)
                .orElseThrow(() -> new CityNotFoundException(
                        "City not found with id: " + id));
    }

    public List<News> getAllNewsFromCity(Long id){
        Optional<City> cityOptional = cityRepository.findCityById(id);
        if(cityOptional.isPresent()){
            return cityOptional.get().getNewsFromCity();
        } else {
            throw new CityNotFoundException("City not found with id: " + id);
        }
    }

    public void addNewsToCity(News news, Long id){
        City city = cityRepository.findCityById(id)
                .orElseThrow(() -> new CityNotFoundException(
                        "City not found with id: " + id));
        city.addNews(news);
    }


}
