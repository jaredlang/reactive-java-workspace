package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        ReactiveSources.intNumbersFlux().count().subscribe(System.out::println); 

        // Collect all items of intNumbersFlux into a single list and print it
        ReactiveSources.intNumbersFlux().log().collectList().subscribe(System.out::println);

        // Transform to a sequence of sums of adjacent two numbers
        // Option 1: not so good. Every time you use function, you block a thread.
        ReactiveSources.intNumbersFlux().log().buffer(2).subscribe(pair -> {
            System.out.println(pair.get(0) + pair.get(1)); 
        }); 
        // Option 2: better. Use reactive operators as much as possible. 
        ReactiveSources.intNumbersFlux().log().buffer(2)
            .map(pair -> pair.get(0) + pair.get(1))
            .subscribe(System.out::println); 

        System.out.println("Press a key to end");
        System.in.read();
    }

}
