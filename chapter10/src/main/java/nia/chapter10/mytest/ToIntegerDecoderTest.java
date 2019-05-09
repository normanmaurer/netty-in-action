package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 * 扩展 ByteToMessageDecoder类,以将字节解码为特定的格式
 * @Date 2019/5/9 15:07
 */
public class ToIntegerDecoderTest extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() >= 4) {
            //从入站ByeBuf中读取一个int,并将其添加到解码消息的List中
            out.add(in.readerIndex());
        }



    }


}
