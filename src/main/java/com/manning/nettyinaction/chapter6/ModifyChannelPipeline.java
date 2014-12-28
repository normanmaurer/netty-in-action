package com.manning.nettyinaction.chapter6;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;

/**
*
* Listing 6.5 of <i>Netty in Action</i>
 *
 * @author <a href="mailto:norman.maurer@googlemail.com">Norman Maurer</a>
*/
public class ModifyChannelPipeline {
   public static void modifyPipeline() {
       ChannelPipeline pipeline = null; // get reference to pipeline;
       FirstHandler firstHandler = new FirstHandler();
       pipeline.addLast("handler1", firstHandler);
       pipeline.addFirst("handler2", new SecondHandler());
       pipeline.addLast("handler3", new ThirdHandler());

       pipeline.remove("handler3");
       pipeline.remove(firstHandler);

       pipeline.replace("handler2", "handler4", new ForthHandler());

   }

   private static final class FirstHandler extends ChannelHandlerAdapter {

   }

   private static final class SecondHandler extends ChannelHandlerAdapter {

   }

   private static final class ThirdHandler extends ChannelHandlerAdapter {

   }

   private static final class ForthHandler extends ChannelHandlerAdapter {

   }
}
