/**
 * Created by kerr.
 *
 * 代码清单 6-1 释放消息资源 {@link nia.chapter6.DiscardHandler}
 *
 * 代码清单 6-2 使用 SimpleChannelInboundHandler {@link nia.chapter6.SimpleDiscardHandler}
 *
 * 代码清单 6-3 消费并释放入站消息 {@link nia.chapter6.DiscardInboundHandler}
 *
 * 代码清单 6-4 丢弃并释放出站消息 {@link nia.chapter6.DiscardOutboundHandler}
 *
 * 代码清单 6-5 修改 ChannelPipeline {@link nia.chapter6.ModifyChannelPipeline#modifyPipeline()}
 *
 * 代码清单 6-6 从 ChannelHandlerContext 访问 Channel {@link nia.chapter6.WriteHandlers#writeViaChannel()}
 *
 * 代码清单 6-7 通过 ChannelHandlerContext 访问 ChannelPipeline {@link nia.chapter6.WriteHandlers#writeViaChannelPipeline()}
 *
 * 代码清单 6-8 调用 ChannelHandlerContext 的 write()方法 {@link nia.chapter6.WriteHandlers#writeViaChannelHandlerContext()}
 *
 * 代码清单 6-9 缓存到 ChannelHandlerContext 的引用 {@link nia.chapter6.WriteHandler}
 *
 * 代码清单 6-10 可共享的 ChannelHandler {@link nia.chapter6.SharableHandler}
 *
 * 代码清单 6-11 @Sharable 的错误用法 {@link nia.chapter6.UnsharableHandler}
 *
 * 代码清单 6-12 基本的入站异常处理 {@link nia.chapter6.InboundExceptionHandler}
 *
 * 代码清单 6-13 添加 ChannelFutureListener 到 ChannelFuture {@link nia.chapter6.ChannelFutures#addingChannelFutureListener()}
 *
 * 代码清单 6-14 添加 ChannelFutureListener 到 ChannelPromise{@link nia.chapter6.OutboundExceptionHandler}
 */
package nia.chapter6;