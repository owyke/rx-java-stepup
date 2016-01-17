package se.mejsla.stepup.rxjava;

import rx.Observable;

public class Combining {


    /**
     *
     * @param s1
     * @param s2
     * @return an Observable containing the values from {@code s1} and {@code s2} concatenated
     */
    public static Observable<String> concatStrings(Observable<String> s1, Observable<String> s2) {
        return  Observable.concat(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param s1
     * @param s2
     * @return merge the two Observables {@code s1} and {@code s2}
     */
    public static Observable<String> mergeStrings(Observable<String> s1, Observable<String> s2) {
        return  Observable.merge(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings
     * @param interval
     * @return an Observable combining the values from {@code strings} and {@code interval} like this "[stringValue] - [intervalValue]"
     */
    public static Observable<String> zipIt(Observable<String> strings, Observable<Long> interval) {
        return strings.zipWith(interval, (string, time) -> time + " - " + string);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings
     * @param interval
     * @return an Observable combining the latest values from {@code strings} and {@code interval} like this "[intervalValue] - [stringValue]"
     */
    public static Observable<String> combineLatest(Observable<String> strings, Observable<Long> interval) {
        return Observable.combineLatest(strings, interval, (string, counter) -> string + " - " + counter);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param s1
     * @param s2
     * @return the data from the fastest source
     */
    public static Observable<String> getFromFastestSource(Observable<String> s1, Observable<String> s2) {
        return Observable.amb(s1, s2);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }
}
