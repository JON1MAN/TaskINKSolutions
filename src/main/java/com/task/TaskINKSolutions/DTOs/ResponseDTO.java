package com.task.TaskINKSolutions.DTOs;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Entities.State;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDTO {
    private String cityName;
    private String stateName;
    private List<NewsResponse> cityNews = new ArrayList<>();
    private List<NewsResponse> stateNews = new ArrayList<>();
    private List<NewsResponse> globalNews = new ArrayList<>();


}
