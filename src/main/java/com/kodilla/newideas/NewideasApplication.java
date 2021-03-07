package com.kodilla.newideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@SpringBootApplication
public class NewideasApplication {


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(NewideasApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port","8089"));
        app.run(args);

        //SpringApplication.run(NewideasApplication.class, args);
    }

}
