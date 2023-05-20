一共分为四个包:
* **constants**：存放一些网络传输模块公用的常量
* **dto**：用于网络传输的类
* **handler**：里面只有一个用于处理rpc请求的类`RpcRequestHandler`(根据rpc请求调用目标累的目标方法)
* **transport**：用户网络传输相关类（真正传输网络请求的地方。提供了Socket和Netty两种网络传输实现方式）。
