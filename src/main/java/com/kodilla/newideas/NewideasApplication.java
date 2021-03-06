package com.kodilla.newideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@SpringBootApplication
public class NewideasApplication {


    public static void main(String[] args) {
        SpringApplication.run(NewideasApplication.class, args);
    }

}
