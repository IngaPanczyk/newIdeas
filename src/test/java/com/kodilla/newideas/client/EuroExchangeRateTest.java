package com.kodilla.newideas.client;

import com.kodilla.newideas.config.CoreConfiguration;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.web.client.RestTemplate;


@RunWith(MockitoJUnitRunner.class)
public class EuroExchangeRateTest {
    @InjectMocks
    private NbpClient nbpClient;
    @Mock
    private RestTemplate restTemplate =null;
    @Mock
    private CoreConfiguration coreConfiguration;

/*    @Value("${api.nbp.endpoint}")
    private String nbpEndpoint;

    @Autowired
    ExchangeRate exchangeRate;*/

    @Test
    public void getRate() {
  /*      //Given
        ExchangeRate[] exchangeRates = new ExchangeRate[1];
       // exchangeRates[0] = new ExchangeRate("A", "EUR", "EUR", new Rate(), new Map<String, Object>() {
      //  }) {
      //  }))


      //  String response = restTemplate.getForObject(nbpEndpoint, ExchangeRate.class).getRates().get(0).getMid().toString();
    //    System.out.println(response);
    }*/


/*
    @Test
    public void getEur() {
*/

       /* //Given&When
        NbpClient nbpClient = new NbpClient();
        String rate = nbpClient.getEur();
        //Then
        Assert.assertEquals("4.5393", rate);*/

    }
}
