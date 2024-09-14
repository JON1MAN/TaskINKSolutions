package com.task.TaskINKSolutions.Repositories;

import com.task.TaskINKSolutions.Entities.City;
import com.task.TaskINKSolutions.Entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findNewsById(Long id);
    Optional<News> findNewsByTitle(String title);
}
