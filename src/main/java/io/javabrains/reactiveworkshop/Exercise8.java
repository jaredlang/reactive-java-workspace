package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources.intNumbersFluxWithException()
                       .subscribe(
                            i -> System.out.println(i), 
                            // OnError terminates the subscription
                            err -> System.out.println(err.getMessage())
                        ); 

        ReactiveSources.intNumbersFluxWithException()
                       .doOnError(err -> System.out.println(err.getMessage()))
                       // doOnError passes on the error and terminates the subscription
                       .subscribe(i -> System.out.println(i)); 

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                       .onErrorContinue((err, i) -> System.out.println(err.getMessage()))
                       // onErrorContinue passes on the error but won't terminate the subscription
                       .subscribe(i -> System.out.println(i)); 
        
        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                       //.onErrorReturn(-1)
                       // onErrorReturn passes on the error but does terminate the subscription
                       .onErrorResume(err -> Flux.just(-1, -2))
                       // onErrorResume passes on the error but does terminate the subscription
                       .subscribe(i -> System.out.println(i)); 

        System.out.println("Press a key to end");
        System.in.read();
    }

}
