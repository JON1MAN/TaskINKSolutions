package com.task.TaskINKSolutions.Repositories;

import com.task.TaskINKSolutions.Entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findStateByStateId(Long stateId);
    Optional<State> findStateByName(String name);
}
