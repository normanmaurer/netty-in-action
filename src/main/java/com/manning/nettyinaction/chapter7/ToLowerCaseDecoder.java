package com.manning.nettyinaction.chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Listing 7.1  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class ToLowerCaseDecoder extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
        if (in.readableBytes() < 2) {
            return;
        }
        ByteBuf buf = ctx.alloc().buffer();
        while (in.readableBytes() >= 2) {
            char c = in.readChar();
            buf.writeChar(Character.toLowerCase(c));
        }
        out.add(buf);
    }
}

