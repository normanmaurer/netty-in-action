package nia.chapter8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 代码清单 8-3 不兼容的 Channel 和 EventLoopGroup
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class InvalidBootstrapClient {

    public static void main(String args[]) {
        InvalidBootstrapClient client = new InvalidBootstrapClient();
        client.bootstrap();
    }

    /**
     * 代码清单 8-3 不兼容的 Channel 和 EventLoopGroup
     * */
    public void bootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        //创建一个新的 Bootstrap 类的实例，以创建新的客户端Channel
        Bootstrap bootstrap = new Bootstrap();
        //指定一个适用于 NIO 的 EventLoopGroup 实现
        bootstrap.group(group)
            //指定一个适用于 OIO 的 Channel 实现类
            .channel(OioSocketChannel.class)
            //设置一个用于处理 Channel的 I/O 事件和数据的 ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                @Override
                protected void channelRead0(
                    ChannelHandlerContext channelHandlerContext,
                    ByteBuf byteBuf) throws Exception {
                    System.out.println("Received data");
                }
             });
        //尝试连接到远程节点
        ChannelFuture future = bootstrap.connect(
                new InetSocketAddress("www.manning.com", 80));
        future.syncUninterruptibly();
    }
}
