package com.task.TaskINKSolutions.Services;

import com.task.TaskINKSolutions.Entities.News;
import com.task.TaskINKSolutions.Exceptions.NewsNotFoundException;
import com.task.TaskINKSolutions.Repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News getNewsById(Long id){
        return newsRepository.findNewsById(id)
                .orElseThrow(() -> new NewsNotFoundException(
                        "News not found with id: " + id));
    }

}
