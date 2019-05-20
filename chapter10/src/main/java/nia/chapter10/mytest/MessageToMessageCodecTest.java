package nia.chapter10.mytest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Create By LiuTao <br/>
 * 编码器:encoder 将应用消息转换为网络消息
 * 解码器:decoder 将网络消息转换为应用消息
 * @Date 2019/5/9 17:53
 */
public class MessageToMessageCodecTest  extends MessageToMessageCodec<Integer,String> {

    /**
     * 消息解码器 将Integer类型转换为String类型的消息
     * 这个方法调用时会被传入 Integer类型的消息,它将吧他们解码为String类型的消息,
     * 消息会被转发给Channelpipeline中的下一个ChannleInboundHandler
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {

    }

    /**
     *  消息编码器 将String类型的消息转换为Integer类型
     * 对于每个 String类型的消息,这个方法都会被调用,这些消息将会被编码为Integer类型的消息
     * 然后被转发给ChannelPipeline中的下一个ChannelOutboundHandler
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

    }
}
