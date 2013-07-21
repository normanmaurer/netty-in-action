package com.manning.nettyinaction.chapter6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 *
 * Listing 6.10 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class DiscardOutboundHandler
        extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx,
                                     Object msg, ChannelPromise promise) {
        ReferenceCountUtil.release(msg);
        promise.setSuccess();

    }

}

