package com.manning.nettyinaction.chapter6;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * Listing 6.7 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@ChannelHandler.Sharable
public class NotSharableHandler extends ChannelInboundHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        count++;

        System.out.println("inboundBufferUpdated(...) called the "
        + count + " time");
        ctx.fireChannelRead(msg);
    }

}

