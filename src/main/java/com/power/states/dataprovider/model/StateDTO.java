package com.power.states.dataprovider.model;

import java.util.List;


public class StateDTO {

    private List<StateDTOModel> states;

    public StateDTO() {
    }

    public StateDTO(List<StateDTOModel> states) {
        this.states = states;
    }

    public List<StateDTOModel> getData() {
        return states;
    }

    public void setData(List<StateDTOModel> states) {
        this.states = states;
    }


}
