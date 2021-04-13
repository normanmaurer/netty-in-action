package nia.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 代码清单 10-2 ToIntegerDecoder2 类扩展了 ReplayingDecoder
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//扩展 ReplayingDecoder<Void> 以将字节解码为消息
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, //传入的 ByteBuf 是 ReplayingDecoderByteBuf
        List<Object> out) throws Exception {
        //从入站 ByteBuf 中读取 一个 int，并将其添加到解码消息的 List 中
        out.add(in.readInt());
    }
}

