package nia.chapter13;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * Listing 13.3 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class LogEventBroadcaster {
    private Bootstrap bootstrap;
    private InetSocketAddress address;
    private File file;
    private EventLoopGroup group;

    public LogEventBroadcaster(int port, String logfile) throws Exception {
        file = new File(logfile);
        if (!file.exists()) {
            throw new Exception("logfile " + logfile + " not found");
        }
        address = new InetSocketAddress("255.255.255.255", port);
    }

    public static void main(String[] args)
        throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        int port = Integer.parseInt(args[0]);
        String logfile = args[1];
        LogEventBroadcaster broadcaster =
            new LogEventBroadcaster(port, logfile);
        try {
            broadcaster.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != broadcaster) {
                broadcaster.stop();
            }
        }
    }

    public void run()
        throws Exception {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class)
            .option(ChannelOption.SO_BROADCAST, true)
            .handler(new LogEventEncoder(address));

        Channel ch = bootstrap.bind(0).syncUninterruptibly().channel();
        System.out.println("LogEventBroadcaster (" + file.getName() + ") running on port " +
            address.getPort() + ".");
        long pointer = file.length();

        while (true) {
            long len = file.length();
            if (len < pointer) {
                // file was reset
                pointer = len;
            } else if (len > pointer) {
                // Content was added
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pointer);
                String line;
                while ((line = raf.readLine()) != null) {
                    ch.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), line));
                }
                pointer = raf.getFilePointer();
                raf.close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        if (null != group) {
            group.shutdown();
        }
    }
}
