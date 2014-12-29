package com.manning.nettyinaction.chapter12;

import io.netty.channel.ChannelHandler;

/**
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
@ChannelHandler.Sharable
public class SpdyRequestHandler extends HttpRequestHandler {
    @Override
    protected String getContent() {
        return "This content is transmitted via SPDY\r\n";
    }
}
