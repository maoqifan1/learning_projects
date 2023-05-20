package com.maoqifan.rpclearning.remoting.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * rpc请求实体类。当你要调用远程方法时，需要先传输一个RpcRequest
 * 给对方，RpcRequest里包含了要调用的目标方法和类的名称、参数等数据。
 * version字段（服务版本）主要是为后续不兼容升级提供可能。
 * group字段主要用于处理一个接口有多个实现类的情况。
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;


    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
