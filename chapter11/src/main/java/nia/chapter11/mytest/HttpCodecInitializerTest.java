package nia.chapter11.mytest;

import io.netty.channel.*;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import nia.chapter11.WebSocketServerInitializer;

/**
 * Create By LiuTao <br/>
 * 11.2.5 11-6 在服务器端支持WebSocket
 * @Date 2019/5/13 14:46
 */
public class HttpCodecInitializerTest extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean isClient;

    public HttpCodecInitializerTest(SslContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline().addLast(
                new HttpServerCodec(),
                //为握手提供聚合的HttpRequest
                new HttpObjectAggregator(65536),
                //如果被请求的端点是/websocket则处理该握手
                new WebSocketServerProtocolHandler("/websocket"),
                new TextFrameHandler(),
                new BinaryFrameHandler(),
                new ContinuationFrameHandler());
    }

    public static final class TextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            //处理文本数据
        }
    }


    public static final class BinaryFrameHandler extends  SimpleChannelInboundHandler<BinaryFrameHandler>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, BinaryFrameHandler msg) throws Exception {
            //处理二进制数据
        }
    }


    public static final class ContinuationFrameHandler extends  SimpleChannelInboundHandler<ContinuationFrameHandler>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ContinuationFrameHandler msg) throws Exception {
            //继续处理框架
        }
    }
}
