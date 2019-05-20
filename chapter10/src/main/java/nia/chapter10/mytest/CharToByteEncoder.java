package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 * 创建一个编码器
 * 将Char转换为Byte
 * encoder: 编码器  将应用数据转换为网络数据
 * decoder: 解码器 将网络数据转换为应用数据
 * @Date 2019/5/10 15:51
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        out.writeChar(msg);
    }


}
