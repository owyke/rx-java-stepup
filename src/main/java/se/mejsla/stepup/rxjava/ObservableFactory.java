package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.Subscriber;


import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ObservableFactory {

    public static <T>  Observable<T> make(Supplier<T>... s) {
        return make(Arrays.asList(s));
    }


    public static <T> Observable<T> make(List<Supplier<T>> s) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            boolean hasTerminated = false;
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (!hasTerminated) {
                    try {
                        s.forEach(i -> subscriber.onNext(i.get()));
                    } catch (Throwable e) {
                        hasTerminated = true;
                        subscriber.onError(new VerySpecialException(e));
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }
}
