package com.manning.nettyinaction.chapter7;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Listing 7.13 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class CharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
