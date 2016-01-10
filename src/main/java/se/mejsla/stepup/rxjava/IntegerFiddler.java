package se.mejsla.stepup.rxjava;

import rx.Observable;


public class IntegerFiddler {



    public Observable<Integer> sumOfAllEvenNumbers(Observable<Integer> intObs) {
        return intObs.filter((i) -> i % 2 == 0).reduce((i1,i2)-> i1 + i2);
    }

    public Observable<Integer> sumPairs(Observable<Integer> intObs1, Observable<Integer> intObs2) {
        return intObs1.zipWith(intObs2, (i1, i2) -> i1 + i2);
    }



}
