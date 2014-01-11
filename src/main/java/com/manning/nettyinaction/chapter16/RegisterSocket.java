package com.manning.nettyinaction.chapter16;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class RegisterSocket {

    public void register(java.nio.channels.SocketChannel socket, EventLoopGroup egroup) {
        java.nio.channels.SocketChannel mySocket = socket;

        SocketChannel ch = new NioSocketChannel(mySocket);
        EventLoopGroup group = egroup;
        group.register(ch);

    }


}
