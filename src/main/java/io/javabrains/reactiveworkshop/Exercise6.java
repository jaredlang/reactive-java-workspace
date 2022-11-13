package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.time.Duration;

public class Exercise6 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // // Get the value from the Mono into a String variable but give up after 5 seconds
        // String value = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(5)); 
        // System.out.println(value);

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        // // Attempt 1: With window, not a solution
        // var valueList = ReactiveSources.stringNumbersFlux()
        //     .window(Duration.ofSeconds(5))
        //     // After window, collectList returns Mono<List<Flux<String>>>
        //     // Without window, collectList returns Mono<List<String>>
        //     .collectList()
        //     .block();
        // Attempt 2: with block duration. It IS a solution
        var valueList = ReactiveSources.stringNumbersFlux()
            // collectList blocks the whole flux
            .collectList()
            .block(Duration.ofSeconds(5));

        valueList.forEach(v -> {
            System.out.println(v);
        });

        System.out.println("Press a key to end");
        System.in.read();
    }

}
