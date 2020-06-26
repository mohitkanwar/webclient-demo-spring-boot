package com.mk.webclientservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class AsynchController {

    @GetMapping("/asynch")
    public ResponseEntity<Boolean> test() {
        System.out.println("Creating Events");
        WebClient.Builder webClientBuilder = WebClient.builder();

        Mono<Boolean> slowEvent = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/test/slow")
                .retrieve()
                .bodyToMono(Boolean.class)
                .publish(Mono::hasElement);

        Mono<Boolean> slowEventTimeOut = webClientBuilder

                .build()
                .get()
                .uri("http://localhost:8080/test/slow")
                .retrieve()
                .bodyToMono(Boolean.class)
                .publish(Mono::hasElement)
                .timeout(Duration.ofSeconds(8))
                .doOnError(error -> {
                    System.out.println("Slow event timeout resulted in an error" + error.getMessage());
                });

        Mono<Boolean> iseEvent = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/test/ise")
                .retrieve()
                .bodyToMono(Boolean.class)
                .publish(Mono::hasElement)
                .doOnSubscribe(ev -> {
                    System.out.println(" ise event subscribed");
                })
                .doOnError(error -> {
                    System.out.println("ISE resulted in an error" + error.getMessage());
                })
                .doOnSuccess(result -> {
                    System.out.println(" ISE event resulted in success" + result.booleanValue());
                });

        System.out.println("Events created");

        slowEvent.subscribe(result -> {
            System.out.println("The result from slow Event is : " + result);
        });

        System.out.println("Slow event subscribed");

        slowEventTimeOut.subscribe(result -> {
            System.out.println("The result from slow Event timeout is : " + result);
        });

        System.out.println("Slow event (with time out) subscribed");

        iseEvent.subscribe((result) -> {
            System.out.println("The result from ise Event is : " + result);
        }, error -> {
            System.out.println("An error occurred while calling ise : " + error.getMessage());
        });
        System.out.println("ise event subscribed");

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
