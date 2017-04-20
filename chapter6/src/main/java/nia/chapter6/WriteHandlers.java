package nia.chapter6;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DummyChannelPipeline;
import io.netty.util.CharsetUtil;

import static io.netty.channel.DummyChannelHandlerContext.DUMMY_INSTANCE;

/**
 * Created by kerr.
 *
 * Listing 6.6 Accessing the Channel from a ChannelHandlerContext
 *
 * Listing 6.7 Accessing the ChannelPipeline from a ChannelHandlerContext
 *
 * Listing 6.8 Calling ChannelHandlerContext write()
 */
public class WriteHandlers {
    private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DUMMY_INSTANCE;
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DummyChannelPipeline.DUMMY_INSTANCE;

    /**
     * Listing 6.6 Accessing the Channel from a ChannelHandlerContext
     * */
    public static void writeViaChannel() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere
        Channel channel = ctx.channel();
        channel.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     * Listing 6.7 Accessing the ChannelPipeline from a ChannelHandlerContext
     * */
    public static void writeViaChannelPipeline() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere
        ChannelPipeline pipeline = ctx.pipeline(); //get reference form somewhere
        pipeline.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     * Listing 6.8 Calling ChannelHandlerContext write()
     * */
    public static void writeViaChannelHandlerContext() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere;
        ctx.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

}
