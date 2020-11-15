package com.ct.centime.service;

import com.ct.centime.config.ServiceConfig;
import com.ct.centime.dto.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ServiceConfig serviceConfig;

    @Test
    void getHelloString() {
        Mockito.when(restTemplate.getForEntity(serviceConfig.getHelloService(), String.class)).thenReturn(new ResponseEntity("Hello", HttpStatus.OK));
        String response = taskService.getHelloString();
        assertEquals("Hello", response);
    }

    @Test
    void getConcatenatedString() {
        Person person = new Person();
        person.setName("John");
        person.setSurname("Doe");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Person> personRequest = new HttpEntity<>(person, headers);
        Mockito.when(restTemplate.postForEntity(serviceConfig.getConcatenateService(), personRequest, String.class)).thenReturn(new ResponseEntity("John Doe", HttpStatus.OK));
        String response = taskService.getConcatenatedString(person);
        assertEquals("John Doe", response);
    }
}