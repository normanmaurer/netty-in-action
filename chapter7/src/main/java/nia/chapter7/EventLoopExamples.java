package nia.chapter15;

import io.netty.channel.Channel;

import java.util.concurrent.Future;

/**
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EventLoopExamples {
    public static void executeTaskInEventLoop() {
        Channel channel = null; // get reference to channel
        channel.eventLoop().execute(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("Run in the EventLoop");
                }
            });
    }

    public static void executeTaskInEventLoopAndCheck() {
        Channel channel = null; // get reference to channel
        Future<?> future = channel.eventLoop().submit(
            new Runnable() {
                @Override
                public void run() {
                    // Do something
                }
            });
        // ...
        if (future.isDone()) {
            System.out.println("Task complete");
        } else {
            System.out.println("Task not complete yet");
        }
    }

    public static void checkIfInEventLoop() {
        Channel channel = null; // get reference to channel
        if (channel.eventLoop().inEventLoop()) {
            System.out.println("In the EventLoop");
        } else {
            System.out.println("Outside the EventLoop");
        }
    }
}
