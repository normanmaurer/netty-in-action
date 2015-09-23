package com.manning.nettyinaction.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Listing 4.5 and 4.6  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class ChannelOperationExamples {

    /**
     * Listing 4.5
     */
    public static void writingToChannel() {
        Channel channel = null; // Get the channel reference from somewhere
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);//1
        ChannelFuture cf = channel.writeAndFlush(buf);                      //2
        cf.addListener(new ChannelFutureListener() {                        //3
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {                                   //4
                    System.out.println("Write successful");
                } else {
                    System.err.println("Write error");                      //5
                    future.cause().printStackTrace();
                }
            }
        });

    }

    /**
     * Listing 4.6
     */
    public static void writingToChannelManyThreads() {
        final Channel channel = null; // Get the channel reference from somewhere
        final ByteBuf buf = Unpooled.copiedBuffer("your data",
                CharsetUtil.UTF_8).retain();                //1
        Runnable writer = new Runnable() {                  //2
            @Override
            public void run() {
                channel.writeAndFlush(buf.duplicate());
            }
        };
        Executor executor = Executors.newCachedThreadPool();//3

        // write in one thread
        executor.execute(writer);                           //4

        // write in another thread
        executor.execute(writer);                           //5

    }
}
