package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 代码清单 6-3 消费并释放入站消息
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
//扩展了ChannelInboundandlerAdapter
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //通过调用 ReferenceCountUtil.release()方法释放资源
        ReferenceCountUtil.release(msg);
    }
}
