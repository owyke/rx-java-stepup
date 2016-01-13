package se.mejsla.stepup.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class IntegerFiddlerTest {

    final IntegerFiddler instance = new IntegerFiddler();
    final Random rand = new Random(1337);

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


    @Test
    public void sortIntegers() throws Exception {
        List<Integer> ints
        Observable result = instance.sortIntegers(Observable.just(9,3,7,34,5,23), Observable.just(6,17,31,43,33,8));
        TestSubscriber testSubscriber = new TestSubscriber();
        result.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(0,6,6,82));
        testSubscriber.assertCompleted();
    }

    private List<Integer> createListOfIntegers(int len) {
        return rand.ints(len).boxed().collect(Collectors.toList());
    }
}