package io.javabrains.reactiveworkshop;

import java.io.IOException;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.userFlux().subscribe(
            user -> System.out.println(user),
            err -> System.out.println(err.getMessage()),
            () -> System.out.println("User Mono Complete")
        ); 

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>()); 

        System.out.println("Press ENTER to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnNext(T value) {
        System.out.println("MY: " + value.toString() + " received");
        //request(2); //backpressure. Signal how many more the subscriber can handle RATHER THAN get the event
        // If there is no back pressure. The subscriber won't be fired. 
        // The best practice is to call the method on the super class. 
        super.hookOnNext(value);
    }

    @Override
    protected void hookFinally(SignalType type) {
        System.out.println("MY: Finally tide-up");
        super.hookFinally(type);
    }

    @Override
    protected void hookOnCancel() {
        System.out.println("MY: Subscription cancelled");
        super.hookOnCancel();
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("MY: All completed");
        super.hookOnComplete();
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println("MY: Something went error");
        super.hookOnError(throwable);
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("MY: Subscription happened");
        //request(3); //backpressure. Signal how many more the subscriber can handle RATHER THAN get the event
        // If there is no back pressure. The subscriber won't be fired. 
        // The best practice is to call the method on the super class. 
        super.hookOnSubscribe(subscription);
    }

}