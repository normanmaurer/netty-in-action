package com.manning.nettyinaction.chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Listing 7.12  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class CharToByteEncoder extends
        MessageToByteEncoder<Character> {

    @Override
    public void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out)
            throws Exception {
        out.writeChar(msg.charValue());
    }
}

