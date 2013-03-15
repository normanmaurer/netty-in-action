package com.manning.nettyinaction.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Listing 1.2  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class FutureExample {
    public static void run() {
        Runnable task1 = new Runnable() {

            @Override
            public void run() {
                doSomeHeavyWork();
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {

            @Override
            public Integer call() {
                return doSomeHeavyWorkWithResult();
            }

        };

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);
        while (!future1.isDone() || !future2.isDone()) {
            // do something else
        }

    }

    private static void doSomeHeavyWork() {
        // Do some work
    }

    private static Integer doSomeHeavyWorkWithResult() {
        // Do some work
        return 1;
    }

}
