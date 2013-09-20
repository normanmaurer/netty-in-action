package com.manning.nettyinaction.chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * Listing 7.6 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class SquareEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    public void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out)
            throws Exception {
        while (in.readableBytes() >= 2) {
            int value = in.readShort();
            out.writeChar(value * value);
        }
    }
}

