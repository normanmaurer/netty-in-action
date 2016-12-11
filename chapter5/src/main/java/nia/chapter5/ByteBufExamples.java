package nia.chapter5;

import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Listings 5.1 - 5.16  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class ByteBufExamples {

    private void handleArray(byte[] array, int offset, int length) {
        // Your method for processing an array
    }

    /**
     * Listing 5.1
     */
    public void backingArray() {
        ByteBuf heapBuf = null;
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array, offset, length);
        }
    }

    /**
     * Listing 5.2
     */
    public void directBufferDataAccess() {
        ByteBuf directBuf = null;
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);
            handleArray(array, 0, length);
        }
    }

    /**
     * Listing 5.3
     */
    public void compositeBufferPatternUsingByteBuffer() {
        ByteBuffer header = null;
        ByteBuffer body = null;
        // Use an array to hold the message parts
        ByteBuffer[] message = {header, body};
        // Create a new ByteBuffer and user copy to merge the header and body
        ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();
    }

    /**
     * Listing 5.4
     */
    public void compositeBufferPatternUsingCompositeByteBuf() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = null; // can be backing or direct
        ByteBuf bodyBuf = null;   // can be backing or direct
        messageBuf.addComponents(headerBuf, bodyBuf);
        // ...
        messageBuf.removeComponent(0); // remove the header
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }

    /**
     * Listing 5.5
     */
    public void accessingDataInCompositeByteBuf() {
        CompositeByteBuf compBuf = Unpooled.compositeBuffer();
        int length = compBuf.readableBytes();
        byte[] array = new byte[length];
        compBuf.getBytes(compBuf.readerIndex(), array);
        handleArray(array, 0, array.length);
    }

    /**
     * Listing 5.6
     */
    public void accessData() {
        ByteBuf buffer = null;
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            System.out.println((char) b);
        }
    }

    /**
     * Listing 5.7
     */
    public void readAllData() {
        ByteBuf buffer = null;
        while (buffer.isReadable()) {
            System.out.println((char) buffer.readByte());
        }
    }

    /**
     * Listing 5.8
     */
    public void writeData() {
        Random random = new Random();

        // Fills the writeable bytes of a buffer with random integers
        ByteBuf buffer = null;
        while (buffer.readableBytes() >= 4) {
            buffer.writeInt(random.nextInt());
        }
    }

    /**
     * Listing 5.9
     */
    public void usingByteBufProcessorToFindCarriageReturn() {
        ByteBuf byteBuf = null;
        int index = byteBuf.forEachByte(ByteBufProcessor.FIND_CR);
    }

    /**
     * Listing 5.10
     */
    public void sliceByteBuf() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 14);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) == sliced.getByte(0);
    }

    /**
     * Listing 5.11
     */
    public void copyingByteBuf() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 14);
        System.out.println(copy.toString(utf8));
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) != copy.getByte(0);
    }

    /**
     * Listing 5.12
     */
    public void getAndSetUsage() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char) buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte) 'B');
        System.out.println((char) buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }

    /**
     * Listing 5.13
     */
    public void readAndWriteOperationsOnByteBuf() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char) buf.readByte());
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.writeByte((byte) '?');
        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();
    }

    /**
     * Listing 5.14
     */
    public void obtainingByteBufAllocatorReference() {
        Channel channel = null;
        ByteBufAllocator allocator = channel.alloc();
        // ...
        ChannelHandlerContext ctx = null;
        ByteBufAllocator allocator2 = ctx.alloc();
    }

    /**
     * Listing 5.15
     */
    public void referenceCounting() {
        Channel channel = null;
        ByteBufAllocator allocator = channel.alloc();
        // ...
        ByteBuf buffer = allocator.directBuffer();
        assert buffer.refCnt() == 1;
    }

    /**
     * Listing 5.16
     */
    public void releaseReferenceCountedObject() {
        ByteBuf buffer = null;
        boolean released = buffer.release();
    }

}
