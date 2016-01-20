package se.mejsla.stepup.rxjava;

import rx.Observable;

public class Accumulating {

    /**
     *
     * @param integers
     * @return an integer observable where each value is been multiplied with the preceding value/values.
     */
    public static Observable<Integer> multiplying(Observable<Integer> integers) {
        return  integers.scan((v1, v2) -> v1 * v2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }
}
