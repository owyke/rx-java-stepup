package se.mejsla.stepup.rxjava;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.Arrays;

public class Scratch {

    public static void main(String[] args) {
        Iterable<Integer> ints= Arrays.asList(2,4,5,0);


        Action1<Throwable> errorHandler = (e) -> System.out.println("Cannot divide with zero, RTFM!");;
        Action1<Integer> elementHandler = (i) -> System.out.println(100 / i);
        Action0 finishedHandler = () -> System.out.println("Done!");

        iterateEm(ints, elementHandler, errorHandler, finishedHandler);
        observeEm(ints, elementHandler, errorHandler, finishedHandler);

    }

    private static void iterateEm(Iterable<Integer> ints, Action1<Integer> elementHandler, Action1<Throwable> errorHandler, Action0 finishedHandler) {
        try {
            ints.forEach(elementHandler::call);
        } catch (Exception ex) {
            errorHandler.call(ex);
        }
        finishedHandler.call();


    }

    private static void observeEm(Iterable<Integer> ints, Action1<Integer> elementHandler, Action1<Throwable> errorHandler, Action0 finishedHandler) {
        Observable.from(ints)
                .subscribe(elementHandler, errorHandler, finishedHandler);

    }
}
