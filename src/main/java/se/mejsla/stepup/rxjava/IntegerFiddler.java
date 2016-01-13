package se.mejsla.stepup.rxjava;

import javafx.util.Pair;
import rx.Observable;
import rx.Subscriber;


public class IntegerFiddler {



    public Observable sumOfAllEvenNumbers(Observable<Integer> o) {
        return o.filter((i) -> i % 2 == 0).reduce((i1,i2)-> i1 + i2);
    }

    public Observable sumPairs(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.zipWith(o2, (i1, i2) -> i1 + i2);
    }


    public Observable sortIntegers(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.mergeWith(o2).toSortedList();


    }

    public Observable getEvens(Observable<Integer> o1, Observable<Integer> o2) {
        return o1.mergeWith(o2).filter((i) -> i % 2 == 0);


    }
}
