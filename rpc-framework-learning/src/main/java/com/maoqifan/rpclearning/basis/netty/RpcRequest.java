package com.maoqifan.rpclearning.basis.netty;

import lombok.*;

/**
 * @apiNote 客户端请求实体类
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
