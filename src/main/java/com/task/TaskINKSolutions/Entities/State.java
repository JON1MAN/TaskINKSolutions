package com.task.TaskINKSolutions.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stateId;
    String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "state", cascade = CascadeType.ALL)
    List<News> newsFromState = new ArrayList<>();

    public void addNews(News news){
        this.newsFromState.add(news);
    }
}
