package com.manning.nettyinaction.chapter12;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class SpdyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final SSLContext context;

    public SpdyChannelInitializer(SSLContext context) {
        this.context = context;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine engine = context.createSSLEngine();
        engine.setUseClientMode(false);

        NextProtoNego.put(engine, new DefaultServerProvider());
        NextProtoNego.debug = true;

        pipeline.addLast("sslHandler", new SslHandler(engine));
        pipeline.addLast("chooser", new DefaultSpdyOrHttpChooser(1024 * 1024, 1024 * 1024));
    }
}
