package se.mejsla.stepup.rxjava;

import rx.Observable;

public class Lab1 {

    public static Observable<String> helloMap(Observable<String> name) {
        return name.map(s -> "Hello " + s);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }
}
