package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameEncoder;

/**
 * Create By LiuTao <br/>
 *   10.5 将编码转换为解码并发送
 *   如果向实现自己的编码器,请看   WebSocket08FrameEncoder
 * @Date 2019/5/9 16:36
 */
public class ShortToByteEncoderTest extends MessageToByteEncoder<Short> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }

    //TODO 编码器: 将应用数据转换为网络格式 encoding
    //TODO 解码器:将网络格式转换为应用数据 decoding
}
