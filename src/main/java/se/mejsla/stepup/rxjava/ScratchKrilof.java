package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ScratchKrilof {
    public static void main(String[] args) throws InterruptedException {
//        iterableVsObservable();
//        streamVsObservable();
//        intervalAndUnsubscribe();
        switchMap();
//        errors();
//        retry();
    }

    private static void iterableVsObservable() {
        List<String> strings = Arrays.asList("One", "Two", "Three", "Four", "Five");
        Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("---------");

        Observable<String> stringObservable = Observable.from(strings);
        stringObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        // Replace with method reference
    }

    private static void streamVsObservable() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> reduce = integers.stream()
                .skip(3) // (4, 5, 6, 7, 8, 9);
                .limit(5) // (4, 5, 6, 7, 8);
                .map(value -> value * 2) // (8, 10, 12, 14, 16);
                .reduce((v1, v2) -> v1 + v2); // 60
        System.out.println("Iterable/Stream value: " + reduce.get());

        Observable<Integer> integerObservable = Observable.from(integers);
        Observable<Integer> reduce1 = integerObservable
                .skip(3) // (4, 5, 6, 7, 8, 9);
                .take(5) // (4, 5, 6, 7, 8);
                .map(value -> value * 2) // (8, 10, 12, 14, 16);
                .reduce((v1, v2) -> v1 + v2); // 60
        reduce1.subscribe(value -> System.out.println("Obserable value: " + value));
    }

    private static void intervalAndUnsubscribe() throws InterruptedException {
        Observable<Long> interval = Observable.interval(500L, TimeUnit.MILLISECONDS);
        Subscription subscribe = interval.subscribe(System.out::println);
        Thread.sleep(1700);
//        subscribe.unsubscribe();
//        System.out.println("unsubscribing");
//        Thread.sleep(5000);
        System.out.println("Done");
    }

    private static void switchMap() throws InterruptedException {
        Observable<String> stringObservable = Observable.interval(40L, TimeUnit.MILLISECONDS)
                .switchMap(v ->
                        Observable.interval(0L, 10L, TimeUnit.MILLISECONDS)
                                .map(u -> String.format("Observable <%s> : %s", (v + 1), (u))));
        stringObservable.subscribe(System.out::println);
        Thread.sleep(160);
    }

    private static void errors() {
        Observable<Integer> numbers = Observable
                .just("1", "2", "three", "4", "5")
                .map(Integer::parseInt);
//                .onErrorResumeNext(throwable -> Observable.just(-2));
//                .onErrorReturn(throwable -> -1);
//        numbers.subscribe(System.out::println);
        numbers.subscribe(
                System.out::println,
                throwable -> System.out.println("oops"),
                () -> System.out.println("Done"));
    }

    public static void retry() {
        Observable<Object> observable = Observable.create(new Observable.OnSubscribe<Object>() {
            int countDown = 2;

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                if (countDown > 0) {
                    countDown--;
                    subscriber.onError(new RuntimeException("BooHoo!"));
                    return;
                }
                subscriber.onNext(3);
            }
        });

        //observable.subscribe(System.out::println);
        observable.retry(2).subscribe(System.out::println);


    }


}
