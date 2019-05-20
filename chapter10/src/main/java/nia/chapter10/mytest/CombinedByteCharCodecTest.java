package nia.chapter10.mytest;

import io.netty.channel.CombinedChannelDuplexHandler;
import nia.chapter10.ByteToCharDecoder;

/**
 * Create By LiuTao <br/>
 *  10-10 构建一个编解码器
 * @Date 2019/5/10 16:11
 */
public class CombinedByteCharCodecTest extends CombinedChannelDuplexHandler<ByteToCharDecoder,CharToByteEncoder> {

    public CombinedByteCharCodecTest() {
        //解码器,编码器
        super(new ByteToCharDecoder(),new CharToByteEncoder());
    }
}
