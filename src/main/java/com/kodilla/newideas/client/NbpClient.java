package com.kodilla.newideas.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class NbpClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.nbp.endpoint}")
    private String nbpEndpoint;

    public String getEur(){
        try {
            String response = restTemplate.getForObject(nbpEndpoint, EurDto.class).getCode().toString();
            return response;
        }catch (RestClientException e){
            return new String("Error");
        }



    }
}
