package nia.chapter11.mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Create By LiuTao <br/>
 * 11-4 自动压缩HTTP消息
 * @Date 2019/5/13 11:27
 */
public class HttpCompressionInitalizerTest extends ChannelInitializer<Channel> {

    private final boolean isClient;

    public HttpCompressionInitalizerTest(boolean isClient) {
        this.isClient = isClient;
    }

    /***
     *  压缩需添加 pom 依赖
     *   groupId: com.jcraft
     *   artifactId: jzlib
     *   version: 1.1.3
     * */



    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if(isClient){

            pipeline.addLast("codec",new HttpClientCodec());
            //添加 HttpContentDecompressor 以处理来自服务器的压缩内容
            pipeline.addLast("decompressor",new HttpContentDecompressor());
        }else{
            pipeline.addLast("codec",new HttpServerCodec());
            //如果是服务器,则添加 HttpContentDecompressor 来压缩(如果客户端支持它)
            pipeline.addLast("compressor",new HttpContentDecompressor());
        }
    }
}
