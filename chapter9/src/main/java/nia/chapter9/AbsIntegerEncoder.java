package nia.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 代码清单9-3 AbsIntegerEncoder
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//扩展 MessageToMessageEncoder 以将一个消息编码为另外一种格式
public class AbsIntegerEncoder extends
    MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
        ByteBuf in, List<Object> out) throws Exception {
        //检查是否有足够的字节用来编码
        while (in.readableBytes() >= 4) {
            //从输入的 ByteBuf中读取下一个整数，并且计算其绝对值
            int value = Math.abs(in.readInt());
            //将该整数写入到编码消息的 List 中
            out.add(value);
        }
    }
}
