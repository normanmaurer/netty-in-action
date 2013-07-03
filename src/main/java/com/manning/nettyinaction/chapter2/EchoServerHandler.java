package com.manning.nettyinaction.chapter2;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.MessageList;

/**
 * Listing 2.4  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@Sharable
public class EchoServerHandler extends
        ChannelInboundHandlerAdapter {

    @Override
    public void messageReceived(ChannelHandlerContext ctx,
        MessageList<Object> msgs) {
        System.out.println("Server received " + msgs.size() + " messages.");

        ctx.write(msgs).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
