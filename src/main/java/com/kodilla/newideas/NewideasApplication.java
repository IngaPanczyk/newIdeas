package com.kodilla.newideas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;



@Component
@SpringBootApplication
public class NewideasApplication {


    public static void main(String[] args) {
        SpringApplication.run(NewideasApplication.class, args);
    }

}
