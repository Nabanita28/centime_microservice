package com.ct.centime.controller;

import com.ct.centime.config.LogMethodParam;
import com.ct.centime.dto.Person;
import com.ct.centime.dto.Response;
import com.ct.centime.exception.CentimeException;
import com.ct.centime.service.TaskService;
import com.ct.centime.util.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("centime/api/v1")
public class TaskController {

    //service 1
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatus(){
        return  ResponseEntity.ok().body(ServiceStatus.UP.name());
    }

    @LogMethodParam
    @PostMapping(path = "/concatenateStrings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> concatenate(@Valid Person person, BindingResult bindingResult){

        Optional<FieldError> fieldError = bindingResult.getFieldErrors().stream().findAny();

        if(fieldError.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.of(new Error(fieldError.get().getDefaultMessage())));
        }

        try{
            return ResponseEntity.ok().body(Response.of(taskService.getGreetings(person)));
        }catch (CentimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.of(new Error(e.getMessage())));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.of(new Error("Unknown Exception Occurred")));
        }
    }
}
