package nia.chapter11.mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Create By LiuTao <br/>
 * 11-2 添加HTTP支持
 * @Date 2019/5/13 11:01
 */
public class HttpPipelineInitializerTest extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpPipelineInitializerTest(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if(client){
            //如果是客户端,则添加解码器已处理来自服务器的响应
            pipeline.addLast("decoder",new HttpResponseDecoder());
            //添加 编码器 向服务器发送请求
            pipeline.addLast("encoder",new HttpResponseEncoder());
        }else{
            //添加解码器处理来自服务器的数据
            pipeline.addLast("decoder",new HttpRequestDecoder());
            //添加编码器用于向服务器发送数据
            pipeline.addLast("encoder",new HttpResponseEncoder());

        }

    }
}
