package se.mejsla.stepup.rxjava.contract;

import rx.Observable;
import rx.Subscriber;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ObservableFactory {

    public static <T> Observable<T> make(Supplier<T>... s) {
        return make(Arrays.asList(s));
    }


    public static <T> Observable<T> make(final List<Supplier<T>> suppliers) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                for(Supplier<T> supplier : suppliers) {
                    final T t;
                    try {
                        t = supplier.get();
                    } catch (Throwable e) {
                        subscriber.onError(new VerySpecialException(e));
                        return;
                    }
                    subscriber.onNext(t);
                };
                subscriber.onCompleted();

            }

        });
    }
}
