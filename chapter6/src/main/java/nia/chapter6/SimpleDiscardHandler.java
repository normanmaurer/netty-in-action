package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 代码清单 6-2 使用 SimpleChannelInboundHandler
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
//扩展了SimpleChannelInboundHandler
public class SimpleDiscardHandler
    extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
        Object msg) {
        //不需要任何显式的资源释放
        // No need to do anything special
    }
}
