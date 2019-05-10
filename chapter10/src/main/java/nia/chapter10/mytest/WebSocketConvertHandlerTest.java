package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.*;

import java.util.List;

/**
 * Create By LiuTao <br/>
 *  创建一个Socket转换工具类
 * @Date 2019/5/10 14:13
 */
@Sharable
public class WebSocketConvertHandlerTest extends MessageToMessageCodec<WebSocketFrame,WebSocketConvertHandlerTest.MyWebSocketFrame> {

    //编码器
    @Override
    protected void encode(ChannelHandlerContext ctx, MyWebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.getData().duplicate().retain();

        switch(msg.getType()){

            case BINARY:
                out.add( new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                out.add( new TextWebSocketFrame(payload));

                break;

            case CLOSE:
                out.add(new CloseWebSocketFrame(true,0,payload));
                break;

            case CONTINUATION:
                out.add(new ContinuationWebSocketFrame(payload));
                break;

            case PONG:
                out.add(new PongWebSocketFrame(payload));
                break;
            case PING:
                out.add(new PingWebSocketFrame(payload));
                break;
           default:
               new IllegalStateException("不受支持的websock消息" + msg);
        }

    }
    //解码器
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {

        ByteBuf payload = msg.content().duplicate();

        if(msg instanceof  BinaryWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.BINARY,payload));
        }else if(msg instanceof CloseWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CLOSE,payload));
        }else if(msg instanceof  PingWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PING,payload));
        }else if(msg instanceof PongWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PONG,payload));
        }else if(msg instanceof  TextWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.TEXT,payload));
        }else if(msg instanceof ContinuationWebSocketFrame){
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CONTINUATION,payload));
        }else{
            new IllegalStateException("不受支持的websock消息" + msg);
        }





    }

    public static final class MyWebSocketFrame{

        public enum FrameType{
            BINARY,
            CLOSE,
            PING,
            PONG,
            TEXT,
            CONTINUATION
        }


        private final FrameType type;
        private final ByteBuf data;

        public MyWebSocketFrame(FrameType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        public FrameType getType() {
            return type;
        }

        public ByteBuf getData() {
            return data;
        }
    }



}


