package com.kodilla.newideas.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbpClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.nbp.endpoint}")
    private String nbpEndpoint;

    public String getEur() {

        String response = restTemplate.getForObject(nbpEndpoint, ExchangeRate.class).getRates().get(0).getMid().toString();
        return response;

    }
}
