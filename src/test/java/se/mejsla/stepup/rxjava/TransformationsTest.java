package se.mejsla.stepup.rxjava;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observables.GroupedObservable;
import rx.observers.TestSubscriber;

import java.util.Arrays;

public class TransformationsTest {

    TestSubscriber<Object> testSubscriber;

    @Before
    public void before() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void helloMapTest() {
        Observable<String> observable = Transformations.helloMap(Observable.just("Jane Doe"));

        observable.subscribe(testSubscriber);
        testSubscriber.assertValue("Hello Jane Doe");
        testSubscriber.assertCompleted();
    }

    @Test
    public void flatMapTest() {
        Observable<Integer> observable = Transformations.mapToRange(Observable.just(1, 3, 5), 2);

        observable.subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 3, 4, 5, 6));
        testSubscriber.assertCompleted();
    }

    @Test
    public void groupingTest() {
        Observable<GroupedObservable<Integer, String>> group =
                 Transformations.groupBySentenceLength(Observable.just(
                        "Love You Till Tuesday", "The Prettiest Star", "Hang On to Yourself", "Holy Holy", "Life on Mars?"));

        group.subscribe(testSubscriber);
        testSubscriber.assertValueCount(3);

        group.forEach(o -> {
            System.out.println(o.getKey());
            o.subscribe(System.out::println);
        });
        Assert.assertTrue("Not tested", false);
    }
}