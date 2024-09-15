package com.task.TaskINKSolutions.Services;

import com.task.TaskINKSolutions.Entities.State;
import com.task.TaskINKSolutions.Exceptions.StateNotFoundException;
import com.task.TaskINKSolutions.Repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State findStateById(Long id){
        return stateRepository.findStateByStateId(id)
                .orElseThrow(() ->
                        new StateNotFoundException("State not found with id: " + id)
                        );
    }

    public State createState(State state){
        return stateRepository.save(state);
    }
}
