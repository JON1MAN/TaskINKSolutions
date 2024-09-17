package com.task.TaskINKSolutions.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News {

    private final static int MAX_LENGTH_FOR_DESCRIPTION = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String description;
    private String url;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    public void setDescription(String description){
        if(description.length() > MAX_LENGTH_FOR_DESCRIPTION){
            description = description.substring(0, MAX_LENGTH_FOR_DESCRIPTION);
        }
        this.description = description;
    }

}
