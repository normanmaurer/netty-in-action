package io.netty.channel;

/**
 * Created by kerr.
 */
public class DummyChannelPipeline extends DefaultChannelPipeline {
    public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipeline(null);
    public DummyChannelPipeline(Channel channel) {
        super(channel);
    }
}
