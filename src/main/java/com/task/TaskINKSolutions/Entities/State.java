package com.task.TaskINKSolutions.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "states")
public class State {

    @Id
    Long stateId;
    String name;
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    List<News> newsFromState = new ArrayList<>();

}
