package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.observables.GroupedObservable;

public class Transformations {

    public static Observable<String> helloMap(Observable<String> name) {
        return name.map(s -> "Hello " + s);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param source
     * @param range
     * @return For each value of the incoming integer observable create an integer range
     * with the {@code source} as staring point and {@code range} as the size of the integer range.
     */
    public static Observable<Integer> mapToRange(Observable<Integer> source, int range) {
        return source.concatMap(start -> Observable.range(start, range));
//        return Observable.error(new RuntimeException("Not Implemented"));
    }

    /**
     *
     * @param strings
     * @return strings grouped by length (words are separated by a space)
     */
    public static Observable<GroupedObservable<Integer, String>> groupBySentenceLength(Observable<String> strings) {
        return strings.groupBy(album -> album.split(" ").length);
//        return Observable.error(new RuntimeException("Not Implemented"));
    }
}
