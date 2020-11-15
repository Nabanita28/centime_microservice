package com.ct.centime.service;

import com.ct.centime.config.ServiceConfig;
import com.ct.centime.dto.Person;
import com.ct.centime.exception.CentimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class TaskService {

    private final RestTemplate restTemplate;
    private final ServiceConfig serviceConfig;

    @Autowired
    TaskService(RestTemplate restTemplate, ServiceConfig serviceConfig){
        this.restTemplate = restTemplate;
        this.serviceConfig = serviceConfig;
    }

    public String getGreetings(Person person) {
        return getHelloString()  + " " + getConcatenatedString(person);
    }

    public String getHelloString(){

        ResponseEntity<String> response = restTemplate.getForEntity(serviceConfig.getHelloService(), String.class);
        if(!response.getStatusCode().equals(HttpStatus.OK)){
            throw new CentimeException("Exception occurred with status code " + response.getStatusCode());
        }
        return response.getBody();
    }

    public String getConcatenatedString(Person person){

        if(Objects.nonNull(person.getName()) && Objects.nonNull(person.getSurname())) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Person> personRequest = new HttpEntity<>(person, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(serviceConfig.getConcatenateService(), personRequest, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }else{
                throw new CentimeException("Exception occurred while concatenating strings.");
            }
        }
        throw new CentimeException("Please enter valid values.");
    }

}
