package com.task.TaskINKSolutions.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    State state;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<News> newsFromCity = new ArrayList<>();

    public void addNews(News news){
        this.newsFromCity.add(news);
    }
}
