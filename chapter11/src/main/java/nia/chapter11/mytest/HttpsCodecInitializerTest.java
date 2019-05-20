package nia.chapter11.mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Create By LiuTao <br/>
 * 11.2.4 11-5 使用HTTPS
 * @Date 2019/5/13 13:48
 */
public class HttpsCodecInitializerTest extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean isClient;

    public HttpsCodecInitializerTest(SslContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine engine = context.newEngine(ch.alloc());
        //如果支持HTTPS 则需要把ssl添加到 channelPipeline管道中的第一位
        pipeline.addFirst("ssl",new SslHandler(engine));

        if(isClient){
            //如果是客户端,则添加客户端的Codec
            pipeline.addLast("codec",new HttpClientCodec());
        }else{
            //如果是服务端,则添加服务端的Codec
            pipeline.addLast("codec",new HttpServerCodec());
        }


    }
}
