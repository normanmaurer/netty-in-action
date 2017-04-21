package nia.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 代码清单 11-10 使用 LengthFieldBasedFrameDecoder 解码器基于长度的协议
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class LengthBasedInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(
                //使用 LengthFieldBasedFrameDecoder 解码将帧长度编码到帧起始的前 8 个字节中的消息
                new LengthFieldBasedFrameDecoder(64 * 1024, 0, 8));
        //添加 FrameHandler 以处理每个帧
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler
        extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx,
             ByteBuf msg) throws Exception {
            // Do something with the frame
            //处理帧的数据
        }
    }
}
