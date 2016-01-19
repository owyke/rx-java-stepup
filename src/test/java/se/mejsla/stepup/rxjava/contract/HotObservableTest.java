package se.mejsla.stepup.rxjava.contract;

import org.junit.Test;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.observers.TestSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class HotObservableTest {


//    @Test
//    public void testName() throws Exception {
//        ConnectableObservable<Long> co = Observable.interval(10, 10, TimeUnit.MILLISECONDS).publish();
//        Observable o = HotObservable.applyFilterAndReturnObservable(co, (l) -> l % 2 == 0);
//        o.takeUntil()
//        assertTrue()
//    }


    @Test
    public void testFizzBuzz() throws Exception {
        ConnectableObservable<Long> co = Observable.interval(10, 1, TimeUnit.MILLISECONDS).takeUntil(l -> l >= 100).publish();
        TestSubscriber<String> fizzBuzzSubscriber = new TestSubscriber<>();

        HotObservable.fizzBuzzObservable(co, fizzBuzzSubscriber);
        fizzBuzzSubscriber.awaitTerminalEvent();
        final List<String> onNextEvents = fizzBuzzSubscriber.getOnNextEvents();
        for (int i = 1; i < onNextEvents.size(); i++) {
            String str = onNextEvents.get(i);
            if (i % 3 == 0 && i % 5 == 0) {
                assertEquals("FizzBuzz", str);
            } else if (i % 5 == 0) {
                assertEquals("Buzz", str);
            } else if (i % 3 == 0) {
                assertEquals("Fizz", str);
            } else {
                try {

                    assertEquals(i, Integer.parseInt(str));
                }catch (NumberFormatException nfe) {
                    fail(str + " is not equal to the expected " + i);
                }
            }
        }
        assertEquals(101, onNextEvents.size());

    }

    @Test
    public void sortIntoSubscribers() throws Exception {
        ConnectableObservable<Long> co = Observable.interval(10, 1, TimeUnit.MILLISECONDS).takeUntil(l -> l >= 100).publish();

        TestSubscriber<Long> odds = new TestSubscriber<>();
        TestSubscriber<Long> evens = new TestSubscriber<>();
        HotObservable.oddsAndEvens(co, odds, evens);
        odds.awaitTerminalEvent(2, TimeUnit.SECONDS);
        evens.awaitTerminalEvent(2, TimeUnit.SECONDS);
        assertTrue(odds.getOnNextEvents().stream().allMatch(l -> l % 2 == 1));
        assertTrue(evens.getOnNextEvents().stream().allMatch(l -> l % 2 == 0));
        odds.assertCompleted();
        evens.assertCompleted();

    }
}