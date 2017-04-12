package com.manning.nettyinaction.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Listing 1.3 and 1.4 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
public class ConnectExample {

    public static void connect(Channel channel) {
        // Does not block
        ChannelFuture future = channel.connect(                         //1
                new InetSocketAddress("192.168.0.1", 25));
        future.addListener(new ChannelFutureListener() {                //2
        @Override
        public void operationComplete(ChannelFuture future) {
            if (future.isSuccess()) {                                   //3
                ByteBuf buffer = Unpooled.copiedBuffer(
                        "Hello", Charset.defaultCharset());             //4
                ChannelFuture wf = future.channel()
                        .writeAndFlush(buffer);                         //5
                // ...
            } else {
                Throwable cause = future.cause();                       //6
                cause.printStackTrace();
            }
        }
        });

    }
}
