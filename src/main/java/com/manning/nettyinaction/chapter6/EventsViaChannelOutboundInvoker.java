package com.manning.nettyinaction.chapter6;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 *
 * Listing 6.2, 6.3 and 6.4 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class EventsViaChannelOutboundInvoker {

    /**
     *
     * Listing 6.2 of <i>Netty in Action</i>
     */
    public static void eventsViaChannel(ChannelHandlerContext context) {
        ChannelHandlerContext ctx = context;
        Channel channel = ctx.channel();
        channel.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     *
     * Listing 6.3 of <i>Netty in Action</i>
     */
    public static void eventsViaChannelPipeline(ChannelHandlerContext context) {
        ChannelHandlerContext ctx = context;
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     *
     * Listing 6.4 of <i>Netty in Action</i>
     */
    public static void eventsViaChannelHandlerContext(ChannelHandlerContext context) {
        ChannelHandlerContext ctx = context;
        ctx.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }
}
