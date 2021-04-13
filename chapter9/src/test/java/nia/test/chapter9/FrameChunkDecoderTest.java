package nia.test.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import nia.chapter9.FrameChunkDecoder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 代码清单9-6 测试 FrameChunkDecoder
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

public class FrameChunkDecoderTest {
    @Test
    public void testFramesDecoded() {
        //创建一个 ByteBuf，并向它写入 9 字节
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();

        //创建一个 EmbeddedChannel，并向其安装一个帧大小为 3 字节的 FixedLengthFrameDecoder
        EmbeddedChannel channel = new EmbeddedChannel(
            new FrameChunkDecoder(3));

        //向它写入 2 字节，并断言它们将会产生一个新帧
        assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            //写入一个 4 字节大小的帧，并捕获预期的TooLongFrameException
            channel.writeInbound(input.readBytes(4));
            //如果上面没有 们将会产生一个新帧抛出异常，那么就会到达这个断言，并且测试失败
            Assert.fail();
        } catch (TooLongFrameException e) {
            // expected exception
        }
        //写入剩余的2字节，并断言将会产生一个有效帧
        assertTrue(channel.writeInbound(input.readBytes(3)));
        //将该 Channel 标记为已完成状态
        assertTrue(channel.finish());

        // Read frames
        //读取产生的消息，并且验证值
        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(2), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();
        buf.release();
    }
}
