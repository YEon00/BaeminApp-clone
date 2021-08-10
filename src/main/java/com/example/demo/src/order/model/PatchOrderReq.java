package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchOrderReq {
    private String storeRequest;
    private String deliverRequest;
    private int orderNo;
}
