package nia.chapter11;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by kerr.
 *
 * 代码清单 11-11 使用 FileRegion 传输文件的内容
 */
public class FileRegionWriteHandler extends ChannelInboundHandlerAdapter {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final File FILE_FROM_SOMEWHERE = new File("");

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        File file = FILE_FROM_SOMEWHERE; //get reference from somewhere
        Channel channel = CHANNEL_FROM_SOMEWHERE; //get reference from somewhere
        //...
        //创建一个 FileInputStream
        FileInputStream in = new FileInputStream(file);
        //以该文件的完整长度创建一个新的 DefaultFileRegion
        FileRegion region = new DefaultFileRegion(
                in.getChannel(), 0, file.length());
        //发送该 DefaultFileRegion，并注册一个 ChannelFutureListener
        channel.writeAndFlush(region).addListener(
            new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future)
               throws Exception {
               if (!future.isSuccess()) {
                   //处理失败
                   Throwable cause = future.cause();
                   // Do something
               }
            }
        });
    }
}
