package nia.chapter10.mytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Create By LiuTao <br/>
 * <p>
 *   将数据编码由Integer转换为String类型
 *   encoder: 编码器 将应用数据转换为网络格式
 *   decoder 解码器 将网络数据转换为应用数据
 *   TODO 更多关于MessageToMessageEncoding的专业语法,请查看 ProtobufEncoder
 *   它由google的 Protocol Buffers规范所定义
 * </p>
 * @Date 2019/5/9 16:58
 */
public class IntegerToStringEncoderTest extends MessageToMessageEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
