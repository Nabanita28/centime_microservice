package com.ct.centime.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "service")
@Getter
@Setter
public class ServiceConfig {

    private String helloService;

    private String concatenateService;
}
