package se.mejsla.stepup.rxjava.contract;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.observers.TestObserver;
import rx.observers.TestSubscriber;

import java.util.concurrent.TimeUnit;


public class HotObservable {


    // MÄRKLIGT: filter tar inte ett predikat?
    public static Observable<Long> applyFilterAndReturnObservable(ConnectableObservable<Long> infiniteIntegers, Func1<Long, Boolean> filter) {
        return infiniteIntegers.filter(filter);

    }


    public static void fizzBuzzObservable(ConnectableObservable<Long> infiniteLongs, Observer<String> fizzBuzzed) {
        Observable.merge(
                infiniteLongs.filter(l -> l % 3 == 0 && l % 5 == 0).map(l -> "FizzBuzz"),
                infiniteLongs.filter(l -> l % 3 != 0 && l % 5 == 0).map(l -> "Buzz"),
                infiniteLongs.filter(l -> l % 3 == 0 && l % 5 != 0).map(l -> "Fizz"),
                infiniteLongs.filter(l -> l % 3 != 0 && l % 5 != 0).map(String::valueOf)).subscribe(fizzBuzzed);



        infiniteLongs.connect();

    }


    public static void oddsAndEvens(ConnectableObservable<Long> infiniteIntegers, Observer<Long> odds, Observer<Long> evens) {
        infiniteIntegers.filter(l -> l % 2 == 1).subscribe(odds);
        infiniteIntegers.filter(l -> l % 2 == 0).subscribe(evens);
        infiniteIntegers.connect();

    }


}
