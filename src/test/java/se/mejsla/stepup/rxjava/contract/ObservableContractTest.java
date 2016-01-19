package se.mejsla.stepup.rxjava.contract;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

public class ObservableContractTest {

    TestSubscriber testSubscriber;
    TestSubscriber anotherTestSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber();
        anotherTestSubscriber = new TestSubscriber();
    }

    @Test
    public void expectOnNextAndCompleted() throws Exception {

        final Observable observable = ObservableFactory.make(Arrays.asList(() -> 5));
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(5));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

    }

    @Test
    public void expect2ReceivedAndCompleted() throws Exception {
        final Observable observable = ObservableFactory.make(() -> 2, () -> 4);
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(2, 4));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

    }


    @Test
    public void expectVerySpecialExceptionOnError() throws Exception {
        final Observable observable = ObservableFactory.make(Arrays.asList(() -> {
            throw new RuntimeException();
        }));
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertError(VerySpecialException.class);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertNoValues();
    }

    @Test
    public void assertOnNextAndException() throws Exception {
        final Observable observable = ObservableFactory.make(() -> 2, () -> {
            throw new RuntimeException();
        });
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(2));
        testSubscriber.assertError(VerySpecialException.class);
        testSubscriber.assertNotCompleted();


    }

    @Test
    public void assertNoNextAfterError() throws Exception {
        final Observable observable = ObservableFactory.make(() -> {
            throw new RuntimeException();
        }, () -> 2);
        observable.unsafeSubscribe(testSubscriber);
        testSubscriber.assertNoValues();
        testSubscriber.assertError(VerySpecialException.class);
        testSubscriber.assertNotCompleted();
    }

    /**
     * En error thrown by the subscriber should not be caught or wrapped.
     * The Observable could not possibly know how to handle an error arising in one of its subscribers.
     * Therefore the error should really be handled by the Observer itself.
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testSubscriberThrowsShouldPropagate() throws Exception {
        final Observable observable = ObservableFactory.make(() -> 2);
        observable.subscribe(o -> {throw new RuntimeException();});

    }




    public <T> Supplier<T> friendlySleep(Supplier<T> wrapped, long sleepTimeMillis) {
        return ()-> {
            try {
                Thread.sleep(sleepTimeMillis);
                return wrapped.get();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        };
    }


}