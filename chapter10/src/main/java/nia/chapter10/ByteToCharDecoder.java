package nia.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 代码清单 10-8 ByteToCharDecoder 类
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//扩展了ByteToMessageDecoder
public class ByteToCharDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
        List<Object> out) throws Exception {
        if (in.readableBytes() >= 2) {
            //将一个或者多个 Character 对象添加到传出的 List 中
            out.add(in.readChar());
        }
    }
}

