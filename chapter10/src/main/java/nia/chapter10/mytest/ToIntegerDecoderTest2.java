package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 *  10.2  扩展ReplayingDecoder 将字节解码为消息
 *   ReplayingDecoder 稍慢于 ByteToMessageDecoder
 * @Date 2019/5/9 15:16
 */
public class ToIntegerDecoderTest2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //此处无需判断是否有数据,因为ReplayingDecoder内部实现已经帮你完成了,因为它内部扩展了 ByteToMessageDecoder
        //如果没有足够的字节可用
        out.add(in.readerIndex());




    }
}
