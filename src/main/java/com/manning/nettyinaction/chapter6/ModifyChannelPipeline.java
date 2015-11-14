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
       FirstHandler firstHandler = new FirstHandler();              //1
       pipeline.addLast("handler1", firstHandler);                  //2
       pipeline.addFirst("handler2", new SecondHandler());          //3
       pipeline.addLast("handler3", new ThirdHandler());            //4

       pipeline.remove("handler3");                                 //5
       pipeline.remove(firstHandler);                               //6
       pipeline.replace("handler2", "handler4", new ForthHandler());//7

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
