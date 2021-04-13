package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 代码清单 6-11 @Sharable 的错误用法
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//使用注解@Sharable标注
@Sharable
public class UnsharableHandler extends ChannelInboundHandlerAdapter {
    private int count;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //将 count 字段的值加 1
        count++;
        //记录方法调用，并转发给下一个ChannelHandler
        System.out.println("inboundBufferUpdated(...) called the "
                + count + " time");
        ctx.fireChannelRead(msg);
    }
}

