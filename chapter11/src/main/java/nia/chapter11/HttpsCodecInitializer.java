package nia.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 代码清单 11-5 使用 HTTPS
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean isClient;

    public HttpsCodecInitializer(SslContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine engine = context.newEngine(ch.alloc());
        //将 SslHandler 添加到ChannelPipeline 中以使用 HTTPS
        pipeline.addFirst("ssl", new SslHandler(engine));

        if (isClient) {
            //如果是客户端，则添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            //如果是服务器，则添加 HttpServerCodec
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
