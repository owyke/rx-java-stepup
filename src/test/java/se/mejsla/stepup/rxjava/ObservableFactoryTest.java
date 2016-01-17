package se.mejsla.stepup.rxjava;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.function.Supplier;

public class ObservableFactoryTest {

    TestSubscriber testSubscriber;
    TestSubscriber anotherTestSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber();
        anotherTestSubscriber = new TestSubscriber();
    }

    @Test
    public void testMake() throws Exception {

        ObservableFactory.make(Arrays.asList(() -> 5)).subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(5));
        testSubscriber.assertCompleted();

    }

    @Test
    public void testNMake2() throws Exception {
        ObservableFactory.make(() -> 2, () -> 4).subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(2, 4));
        testSubscriber.assertCompleted();

    }


    @Test
    public void testException() throws Exception {
        ObservableFactory.make(Arrays.asList(() -> {
            throw new RuntimeException();
        })).subscribe(testSubscriber);
        testSubscriber.assertError(VerySpecialException.class);
    }

    @Test
    public void noCallsToOnNextAfterFailure() throws Exception {
        final Observable<Object> observable = ObservableFactory.make(Arrays.asList(() -> {
            throw new RuntimeException();
        }));


        observable.subscribe(testSubscriber);
        observable.subscribe(anotherTestSubscriber);
        testSubscriber.assertError(VerySpecialException.class);
        testSubscriber.assertNoValues();
        testSubscriber.assertNotCompleted();

        anotherTestSubscriber.assertNoValues();
        anotherTestSubscriber.assertNoErrors();
        anotherTestSubscriber.assertNotCompleted();

    }

    @Test
    public void testasd() throws Exception {
        Observable o = Observable.from(Arrays.asList(1,2,3,4,5));
        o.subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        o.subscribe(anotherTestSubscriber);
        anotherTestSubscriber.assertCompleted();


    }

    public <T> T friendlySleep(Supplier<T> wrapped, long sleepTimeMillis) {
        try {
            Thread.sleep(sleepTimeMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        return wrapped.get();
    }


}