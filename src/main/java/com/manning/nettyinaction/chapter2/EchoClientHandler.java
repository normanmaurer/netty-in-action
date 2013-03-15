package com.manning.nettyinaction.chapter2;

import io.netty.buffer.BufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.6 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@Sharable
public class EchoClientHandler extends
    ChannelInboundByteHandlerAdapter {

    private final static ByteBuf MSG = Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.write(MSG.duplicate());
    }

    @Override
    public void inboundBufferUpdated(ChannelHandlerContext ctx,
        ByteBuf in) {
        System.out.println("Client received: " + BufUtil
                .hexDump(in.readBytes(in.readableBytes())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
