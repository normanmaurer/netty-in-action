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
 * 代码清单 6-6 从 ChannelHandlerContext 访问 Channel
 *
 * 代码清单 6-7 通过 ChannelHandlerContext 访问 ChannelPipeline
 *
 * Listing 6.8 Calling ChannelHandlerContext write()
 */
public class WriteHandlers {
    private static final ChannelHandlerContext CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE = DUMMY_INSTANCE;
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_SOMEWHERE = DummyChannelPipeline.DUMMY_INSTANCE;

    /**
     * 代码清单 6-6 从 ChannelHandlerContext 访问 Channel
     * */
    public static void writeViaChannel() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere
        //获取到与 ChannelHandlerContext相关联的 Channel 的引用
        Channel channel = ctx.channel();
        //通过 Channel 写入缓冲区
        channel.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     * 代码清单 6-7 通过 ChannelHandlerContext 访问 ChannelPipeline
     * */
    public static void writeViaChannelPipeline() {
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere
        //获取到与 ChannelHandlerContext相关联的 ChannelPipeline 的引用
        ChannelPipeline pipeline = ctx.pipeline(); //get reference form somewhere
        //通过 ChannelPipeline写入缓冲区
        pipeline.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));

    }

    /**
     * 代码清单 6-8 调用 ChannelHandlerContext 的 write()方法
     * */
    public static void writeViaChannelHandlerContext() {
        //获取到 ChannelHandlerContext 的引用
        ChannelHandlerContext ctx = CHANNEL_HANDLER_CONTEXT_FROM_SOMEWHERE; //get reference form somewhere;
        //write()方法将把缓冲区数据发送到下一个 ChannelHandler
        ctx.write(Unpooled.copiedBuffer("Netty in Action", CharsetUtil.UTF_8));
    }

}
