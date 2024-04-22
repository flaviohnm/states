package com.power.states.controller;


import com.power.states.dataprovider.service.APIService;
import com.power.states.model.State;
import com.power.states.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/states")
public class StateController {

    @Autowired
    private StateService service;

    @Autowired
    private APIService apiService;

    @GetMapping("/list")
    private List<State> list() {
        return service.list();
    }

    @GetMapping("/data")
    public ResponseEntity<String> getStates() {
        String stateList = apiService.getData();
        return ResponseEntity.ok(stateList);
    }
}
