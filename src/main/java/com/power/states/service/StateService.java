package com.power.states.service;


import com.power.states.model.State;
import com.power.states.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    public State createState(State state) {

        return repository.save(state);
    }

    public List<State> list() {
        return repository.findAll();
    }

}
