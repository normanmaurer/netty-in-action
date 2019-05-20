package nia.chapter11.mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By LiuTao <br/>
 * 11-3 自动聚合HTTP的消息片段
 * @Date 2019/5/13 11:15
 */
public class HttpAggregatorInitializerTest extends ChannelInitializer<Channel> {

    private final boolean isClient;

    public HttpAggregatorInitializerTest(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if(isClient){
            //如果是客户端,则添加HttpClientCodec
            pipeline.addLast("codec",new HttpClientCodec());
        }else{
            //如果是服务器,则添加HttpServerCodec
            pipeline.addLast("codec",new HttpServerCodec());
        }

        //将最大的消息大小为512kb的HttpObjectAggregator添加到ChannelPipeline中
        pipeline.addLast("aggregator",new HttpObjectAggregator(512 * 1024));

    }

}
