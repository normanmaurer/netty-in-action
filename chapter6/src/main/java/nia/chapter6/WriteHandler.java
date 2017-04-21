package nia.chapter6;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 代码清单 6-9 缓存到 ChannelHandlerContext 的引用
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class WriteHandler extends ChannelHandlerAdapter {
    private ChannelHandlerContext ctx;
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        //存储到 ChannelHandlerContext的引用以供稍后使用
        this.ctx = ctx;
    }
    public void send(String msg) {
        //使用之前存储的到 ChannelHandlerContext的引用来发送消息
        ctx.writeAndFlush(msg);
    }
}
