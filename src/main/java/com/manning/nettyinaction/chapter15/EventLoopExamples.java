package com.manning.nettyinaction.chapter15;

import io.netty.channel.Channel;

import java.util.concurrent.Future;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class EventLoopExamples {
    /**
     * Listing 14.1
     */
    public static void executeTaskInEventLoop() {
        Channel channel = null; // get reference to channel
        channel.eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run in the EventLoop");
            }
        });
    }

    /**
     * Listing 14.2
     */
    public static void executeTaskInEventLoopAndCheck() {
        Channel channel = null; // get reference to channel
        Future<?> future = channel.eventLoop().submit(new Runnable() {
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

    /**
     * Listing 14.3
     */
    public static void checkIfInEventLoop() {
        Channel channel = null; // get reference to channel
        if (channel.eventLoop().inEventLoop()) {
            System.out.println("In the EventLoop");
        } else {
            System.out.println("Outside the EventLoop");
        }
    }
}
