package com.manning.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;
    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine engine = context.newEngine(ch.alloc());

        ch.pipeline().addFirst("ssl", new SslHandler(engine, startTls));
    }
}
