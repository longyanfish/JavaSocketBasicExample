# JavaSocketBasicExample
这是在学习Socket网络编程时写的两个最基本的小例子，适合刚接触Socket的人去看，不是什么深入研究之后的大工程项目

SocketExample是一个最基本的Socket的入门例子，一个机器作为客户端和另一个机器作为一个服务端来进行通信，这个客户端连接服务器端，向服务器端发送一句话，

服务器端把这句话读取出来，然后回了一句话给这个客户端，客户端接收服务器的这句话读取出来。

而ChatServer这个小项目也是一个关于Socket的小例子，不过他更复杂了一些，是一个服务器端和多个客户端进行通信的小例子，但是并不符合多人聊天的标准，因为

这里对客户端发送来的消息并没有排序，如果是聊天的话多个用户的聊天就是乱糟糟，然后最关键客户端的数据都是写死了，没有从键盘输入话语这几句代码。

这里在服务器端采用了多线程去处理多个客户端的信息。
