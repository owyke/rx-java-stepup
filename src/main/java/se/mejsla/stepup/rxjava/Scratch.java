package se.mejsla.stepup.rxjava;

import rx.Observable;

public class Scratch {

    public static void main(String[] args) {
        Integer[] ints= {1,2,3,4,5};
        Observable.from(ints).subscribe(System.out::println);
    }
}
