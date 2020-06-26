package com.mk.webclientservice;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class FluxReceiverController {

//    @GetMapping("/asynch")
//    public ResponseEntity<Boolean> test() {
//        WebClient.Builder webClientBuilder = WebClient.builder();
//         Flux<Book> bookFlux = webClientBuilder
//
//                .build()
//                .get()
//                .uri("http://localhost:8080/books/stream")
//                .retrieve()
//                .bodyToFlux(Book.class);
//
//
//         bookFlux.buffer(3).collect(books->{
//             System.out.println(books);
//         });
//    }
}
