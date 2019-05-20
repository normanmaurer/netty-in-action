package nia.chapter11.mytest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Create By LiuTao <br/>
 * 11.3 空闲的连接和超时
 * 11-7 发送心跳
 * @Date 2019/5/13 15:20
 */
public class IdStateHandlerInitializerTest extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new IdleStateHandler(0,0,60,TimeUnit.SECONDS));
        //将一个HeartbeatHandler添加到channelPipeline中
        pipeline.addLast(new HeartbeatHandler());
    }
    public static final class HeartbeatHandler extends ChannelInboundHandlerAdapter{
        //发送到远程节点的消息
        private static final ByteBuf HEARTBEAT_SQEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
                "HEARTBEAT",CharsetUtil.ISO_8859_1));


        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if(evt instanceof IdleStateEvent){
                //发送心跳消息,如果连接失败,则关闭连接
                ctx.writeAndFlush(HEARTBEAT_SQEQUENCE.duplicate())
                        .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }else{
                //如果不是IdleStateEvent事件,所以将它传递给下一个ChannelInboundHandler
                super.userEventTriggered(ctx,evt);
            }
        }
    }



}
