package com.bookin.booking.webService;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;


@Service
public class EmailWebService {

    @Value("${app.email.url}")
    private String emailUrl;

    private WebClient webClient;

    public EmailWebService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl( emailUrl ).build();
    }

    public  void sendNotificationEmail(String toAddress, String name) {
        System.out.println("inside webclient");
        webClient.get().uri("sendBookingNotification?toAddress={toAddress}&name={name}", toAddress, name);
    }


}
