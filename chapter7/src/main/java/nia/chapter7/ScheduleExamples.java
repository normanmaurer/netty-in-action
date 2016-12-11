package nia.chapter7;

import io.netty.channel.Channel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class ScheduleExamples {
    public static void schedule() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        ScheduledFuture<?> future =
            executor.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Now it is 60 seconds later");
                    }
                }, 60, TimeUnit.SECONDS
            );
        // do something
        //

        executor.shutdown();
    }

    public static void scheduleViaEventLoop() {
        Channel ch = null; // Get reference to channel
        ScheduledFuture<?> future =
            ch.eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Now its 60 seconds later");
                    }
                }, 60, TimeUnit.SECONDS
            );
    }

    public static void scheduleFixedViaEventLoop() {
        Channel ch = null; // Get reference to channel
        ScheduledFuture<?> future =
            ch.eventLoop().scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(
                            "Run every 60 seconds");
                    }
                }, 60, 60, TimeUnit.SECONDS
            );
    }
}
