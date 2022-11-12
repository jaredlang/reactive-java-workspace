package io.javabrains.reactiveworkshop;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()
        Consumer<Integer> consumerPrintInt = i -> {
            System.out.println(i); 
        };
        Consumer<User> consumerPrintUserFirstName = user -> {
            System.out.println(user.getFirstName());
        };

        // Print all numbers in the intNumbersStream stream
        System.out.println("*** All Numbers in StreamSources");
        StreamSources.intNumbersStream()
                     .forEach(consumerPrintInt);

        // Print numbers from intNumbersStream that are less than 5
        System.out.println("*** All Numbers less than 5");
        StreamSources.intNumbersStream()
                     .filter(i -> i < 5)
                     .forEach(consumerPrintInt); 

        // Print the second and third numbers in intNumbersStream that's greater than 5
        System.out.println("*** The second and third Numbers greater than 5");
        // TODO: Write code here
        StreamSources.intNumbersStream()
                     .filter(i -> i > 5)
                     .skip(1)
                     .limit(2)
                     .forEach(consumerPrintInt);

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        System.out.println("*** The first number greater than 5 or -1");
        StreamSources.intNumbersStream()
                     .filter(i -> i > 5)
                     .findFirst()
                     .ifPresentOrElse(
                        consumerPrintInt, 
                        () -> { System.out.println(-1); }
                    );

        // Print first names of all users in userStream
        System.out.println("*** The first names of all users in userStream");
        StreamSources.userStream()
                     .forEach(consumerPrintUserFirstName);
        System.out.println("---- OR ----");
        StreamSources.userStream()
                     .map(user -> user.getFirstName())
                     .forEach(firstName -> System.out.println(firstName)); 
                      

        // Print first names in userStream for users that have IDs from number stream
        System.out.println("*** The first names of all users whose ids are in intNumbersStream");
        StreamSources.userStream()
                     .filter(user -> StreamSources.intNumbersStream().anyMatch(i -> i == user.getId()))
                     .forEach((user) -> {
                        System.out.println(String.join(":", new String[] {
                            String.format("%d", user.getId()), user.getFirstName()
                        }));
                     });
        System.out.println("---- OR ----");
        StreamSources.intNumbersStream()
                     .flatMap(i -> StreamSources.userStream().filter(user -> user.getId() == i))
                     .map(user -> user.getFirstName())
                     .forEach(firstName -> System.out.println(firstName));
    }

}
