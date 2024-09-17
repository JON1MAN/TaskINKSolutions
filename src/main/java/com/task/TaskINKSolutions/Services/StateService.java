package com.task.TaskINKSolutions.Services;

import com.task.TaskINKSolutions.Entities.State;
import com.task.TaskINKSolutions.Exceptions.StateNotFoundException;
import com.task.TaskINKSolutions.Repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public State findStateByName(String name){
        return stateRepository.findStateByName(name)
                .orElseThrow(() ->
                        new StateNotFoundException("State not found with name: " + name)
                );
    }

    public List<State> getAllStates(){
        return stateRepository.findAll();
    }

    public State createState(State state){
        Optional<State> optionalState =
                stateRepository.findStateByName(state.getName());
        if(optionalState.isPresent()){
            System.out.println("STATE ALREADY EXISTS");
            return optionalState.get();
        }else {
            return stateRepository.save(state);
        }

    }
}
