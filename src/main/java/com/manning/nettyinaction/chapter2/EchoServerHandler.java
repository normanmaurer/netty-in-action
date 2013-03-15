package com.manning.nettyinaction.chapter2;

import io.netty.buffer.BufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundByteHandlerAdapter;

/**
 * Listing 2.4  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@Sharable
public class EchoServerHandler extends 
    ChannelInboundByteHandlerAdapter {

    @Override
    public void inboundBufferUpdated(ChannelHandlerContext ctx,
        ByteBuf in) {
        System.out.println("Server received: " + BufUtil
            .hexDump(in));

        ByteBuf out = ctx.nextOutboundByteBuffer();
        out.discardReadBytes();
        out.writeBytes(in);
        ctx.flush().addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
