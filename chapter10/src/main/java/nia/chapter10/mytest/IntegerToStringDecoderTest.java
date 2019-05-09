package nia.chapter10.mytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 *   提供一个int 转换为String类型的编码解析器
 *   /TODO 如果想看更复杂的例子,请看HttpObjectAggregator
 *
 * @Date 2019/5/9 15:55
 */
public class IntegerToStringDecoderTest extends MessageToMessageDecoder<Integer> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}





