package nia.chapter11.mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Create By LiuTao <br/>
 * 11-1 添加SSL支持
 * @Date 2019/5/10 17:38
 */
public class SslChannelInitializerTest extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;

    public SslChannelInitializerTest(SslContext context, boolean startTls) {
        this.context = context;
        //如果设置为true 第一个写入到消息将不会被加密(客户端应该设置为true)
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //对于每个SslHandler实例,都使用Channel的ByteBufAllocator从SslContext获取一个新的SSLEngine
        SSLEngine engine = context.newEngine(ch.alloc());
        //将SslHandler作为第一个ChannelHandler添加到ChannelPipeline中
        ch.pipeline().addFirst("ssl",new SslHandler(engine,startTls));

    }
}
