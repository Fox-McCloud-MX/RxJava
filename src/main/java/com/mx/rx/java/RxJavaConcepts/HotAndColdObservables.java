package com.mx.rx.java.RxJavaConcepts;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.Arrays;

public class HotAndColdObservables {
    public static void main(String[] args) {
        System.out.println("Hot");
        hot().blockingSubscribe(System.out::println);
        System.out.println("Cold");
        cold().blockingSubscribe(System.out::println);
    }

    private static Observable<String> hot() {
        return new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                // Here we have the possibility of having an infinite (unbounded) source of events, or an unknown
                // number of events. Another difference with a cold observable, is that a hot observable may call
                // onError to report errors to subscribers
                observer.onNext("A");
                observer.onNext("B");
                observer.onNext("C");
                observer.onComplete();
            }
        };
    }

    private static Observable<String> cold() {
        return Observable.fromIterable(Arrays.asList("A", "B", "C"));
    }
}
