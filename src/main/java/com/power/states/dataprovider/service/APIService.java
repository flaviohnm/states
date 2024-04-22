package com.power.states.dataprovider.service;

import com.google.gson.Gson;
import com.power.states.dataprovider.model.StateDTO;
import com.power.states.model.State;
import com.power.states.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {

    @Autowired
    private StateRepository repository;

    private RestTemplate restTemplate = new RestTemplate();

    private Gson gson = new Gson();

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    public String getData() {

        String url = "https://www.healthcare.gov/api/states.json";
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String responseBody = responseEntity.getBody();
            StateDTO stateList = gson.fromJson(responseBody, StateDTO.class);

            repository.deleteAll();
            System.out.println("Dados Apagados");

            stateList.getData()
                    .stream()
                    .filter(state -> state.getLang().equals("en"))
                    .forEach(
                            state -> {
                                State newState = new State();
                                newState.setTitle(state.getTitle());
                                newState.setUrl(state.getUrl());
                                repository.save(newState);
                            });

            System.out.println("Dados Carregados");
            return "Estados Cadastrados";
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error Calling Api: " + e.getMessage());
        }
    }

}
