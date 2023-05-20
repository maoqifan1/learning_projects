package com.maoqifan.rpclearning.basis.netty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RpcResponse {
    private String message;
}
