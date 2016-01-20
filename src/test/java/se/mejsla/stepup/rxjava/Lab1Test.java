package se.mejsla.stepup.rxjava;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

public class Lab1Test {

    TestSubscriber<Object> testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void helloMapTest() {
        Observable<String> observable = Lab1.helloMap(Observable.just("Jane Doe"));

        observable.subscribe(testSubscriber);
        testSubscriber.assertValue("Hello Jane Doe");
        testSubscriber.assertCompleted();
    }
}