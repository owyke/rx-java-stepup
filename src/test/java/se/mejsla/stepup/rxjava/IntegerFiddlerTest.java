package se.mejsla.stepup.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IntegerFiddlerTest {

    final IntegerFiddler instance = new IntegerFiddler();


    @Test
    public void testSumEqual() {
        final TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        instance.sumOfAllEvenNumbers(Observable.just(2,7,13,4,8,44,5,7))
            .subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(58));
    }


    @Test
    public void testSumPairs() throws Exception {
        Observable result = instance.sumPairs(Observable.just(0,2,3,5), Observable.just(0,4,3,77));
        TestSubscriber testSubscriber = new TestSubscriber();
        result.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(0,6,6,82));
        testSubscriber.assertCompleted();
    }
}