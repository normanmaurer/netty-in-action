package com.manning.nettyinaction.chapter1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class GatheringWriteExample {

    public void gatheringWrite(GatheringByteChannel channel) throws IOException {
        ByteBuffer header = ByteBuffer.wrap("YourHeader".getBytes());
        ByteBuffer body   = ByteBuffer.wrap("YourBody".getBytes());

        ByteBuffer[] buffers = { header, body };

        channel.write(buffers);
    }
}
