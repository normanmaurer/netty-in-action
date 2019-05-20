package nia.chapter10.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 * 10-8
 *  将字节转换为Character
 * @Date 2019/5/10 15:46
 */
public class ByteToChanDecoderText  extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if(in.readableBytes() >2){
            //这里将会自动装箱为Char类型,类型向上转型为自动装箱
            out.add(in.readChar());
        }
    }
}

