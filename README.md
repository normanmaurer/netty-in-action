# <<Netty 实战>>

[预售(即将开始4月下旬)-人民邮电出版社-异步社区](http://www.epubit.com.cn)

![image](https://cloud.githubusercontent.com/assets/501740/25295296/94d2ef06-2715-11e7-9a2a-916d77014cfc.png)


## 内容提要

本书是为想要或者正在使用 Java 从事高性能网络编程的人而写的，循序渐进地介绍了 Netty
各个方面的内容。

本书共分为 4 个部分：第一部分详细地介绍 Netty 的相关概念以及核心组件，第二部分介绍
自定义协议经常用到的编解码器，第三部分介绍 Netty 对于应用层高级协议的支持，会覆盖常见
的协议及其在实践中的应用，第四部分是几个案例研究。此外，附录部分还会简单地介绍 Maven，
以及如何通过使用 Maven 编译和运行本书中的示例。

阅读本书不需要读者精通 Java 网络和并发编程。如果想要更加深入地理解本书背后的理念
以及 Netty 源码本身，可以系统地学习一下 Java 网络编程、 NIO、并发和异步编程以及相关的
设计模式。

## 说明

这个仓库包含了[Netty In Action](http://www.manning.com/maurer/) 这本书的中文版 [Netty实战](http://www.epubit.com.cn) 的代码清单.
为了更好地服务于读者,进行了如下方面的改进.


相对于英文版本([2.0-SNAPSHOT](https://github.com/ReactivePlatform/netty-in-action-cn/tree/2.0-SNAPSHOT)分支):

1. 更新了行文中的注释
2. 按照中文版本的排版进行了调整
3. 所有的代码清单以及跳转都使用了中文版书籍中的翻译


## 反馈

上游版本的更新,请直接将PR的目标调整为本仓库的`master`分支

中文版本的更新,请将PR的目标调整为本仓库的`ChineseVersion`分支

## 使用

请直接克隆本项目即可,建议对照原文查看代码.

-----

Prerequisites

    maven 3.3.9
    JDK 8


If you want to build everything at once, from the top directory run

	mvn install


If you want to build only single projects then from the top directory first run

	mvn install -pl utils


This will make the utils jar available to all the projects.
