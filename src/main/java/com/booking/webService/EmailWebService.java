package com.booking.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;


@Service
public class EmailWebService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${app.email.url}")
    private String emailUrl;

    private WebClient webClient;

    public EmailWebService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl( emailUrl ).build();
    }

    public  void sendNotificationEmail(String toAddress, String name) {
        System.out.println("inside webclient");
        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/email/sendBookingNotification")
                        .queryParam("toAddress", toAddress)
                        .queryParam("name", name)
                        .build() )
                .retrieve();
        //webClient.post().uri("{emailUrl}/sendBookingNotification?toAddress={toAddress}&name={name}", emailUrl, toAddress, name);
        ///verifyCalledUrl("http://192.168.56.102:30088/api/email");

        System.out.println(" webclient called!");

        UriComponentsBuilder queryStr = UriComponentsBuilder.fromUriString(emailUrl + "/api/email/sendBookingNotification" )
                // Add query parameter
                .queryParam("toAddress", toAddress)
                .queryParam("name", name);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        restTemplate.exchange(queryStr.build().toUri() , HttpMethod.POST, entity, String.class).getBody();

        System.out.println("restTemplate called!");
        //System.out.println("{emailUrl}/sendBookingNotification?toAddress={toAddress}&name={name}", emailUrl, toAddress, name);

    }


}
