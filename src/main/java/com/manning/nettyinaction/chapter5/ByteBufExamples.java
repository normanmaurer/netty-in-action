package com.manning.nettyinaction.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by norman on 29/12/14.
 */
public class ByteBufExamples {

    private final static Random random = new Random();

    /**
     * Listing 5.1
     */
    public static void heapBuffer(ByteBuf heapBuf) {
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
    public static void directBuffer(ByteBuf directBuf) {
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
    public static void byteBufferComposite(ByteBuffer header, ByteBuffer body) {
        // Use an array to hold the message parts
        ByteBuffer[] message = { header, body };

        // Use copy to merge both
        ByteBuffer message2 = ByteBuffer.allocate(
                header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();
    }


    /**
     * Listing 5.4
     */
    public static void byteBufComposite(ByteBuf headerBuf, ByteBuf bodyBuf) {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        messageBuf.addComponents(headerBuf, bodyBuf);
        // ....
        messageBuf.removeComponent(0); // remove the header				//2

        for (int i = 0; i < messageBuf.numComponents(); i++) {						//3
            System.out.println(messageBuf.component(i).toString());
        }
    }

    /**
     * Listing 5.5
     */
    public static void byteBufCompositeArray(CompositeByteBuf compBuf) {
        int length = compBuf.readableBytes();						//1
        byte[] array = new byte[length];						//2
        compBuf.getBytes(compBuf.readerIndex(), array);							//3
        handleArray(array, 0, length);
    }

    /**
     * Listing 5.6
     */
    public static void byteBufRelativeAccess(ByteBuf buffer) {
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            System.out.println((char) b);
        }

    }

    /**
     * Listing 5.7
     */
    public static void readAllData(ByteBuf buffer) {
        while (buffer.isReadable()) {
            System.out.println(buffer.readByte());
        }
    }

    /**
     * Listing 5.8
     */
    public static void write(ByteBuf buffer) {
        while (buffer.writableBytes() >= 4) {
            buffer.writeInt(random.nextInt());
        }
    }

    /**
     * Listing 5.9
     */
    public static void byteBufProcessor(ByteBuf buffer) {
        int index = buffer.forEachByte(ByteBufProcessor.FIND_CR);
    }

    /**
     * Listing 5.10
     */
    public static void byteBufSlice() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);     //1

        ByteBuf sliced = buf.slice(0, 14);                                       //2
        System.out.println(sliced.toString(utf8));                               //3

        buf.setByte(0, (byte) 'J');                                              //4
        assert buf.getByte(0) == sliced.getByte(0);
    }

    /**
     * Listing 5.11
     */
    public static void byteBufCopy() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);     //1

        ByteBuf copy = buf.copy(0, 14);                                          //2
        System.out.println(copy.toString(utf8));                                 //3

        buf.setByte(0, (byte) 'J');                                              //4
        assert buf.getByte(0) != copy.getByte(0);
    }

    /**
     * Listing 5.12
     */
    public static void byteBufSetGet() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);	//1
        System.out.println((char)buf.getByte(0));					//2

        int readerIndex = buf.readerIndex();						//3
        int writerIndex = buf.writerIndex();

        buf.setByte(0, (byte)'B');							//4

        System.out.println((char)buf.getByte(0));					//5
        assert readerIndex == buf.readerIndex();					//6
        assert writerIndex ==  buf.writerIndex();
    }

    /**
     * Listing 5.13
     */
    public static void byteBufWriteRead() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);	//1
        System.out.println((char)buf.readByte());					//2

        int readerIndex = buf.readerIndex();						//3
        int writerIndex = buf.writerIndex();						//4

        buf.writeByte((byte)'?');							//5

        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();
    }

    private static void handleArray(byte[] array, int offset, int len) {

    }
}
