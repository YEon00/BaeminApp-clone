package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderReq {
    private int orderNo;
    private int basketNo;
    private String storeRequest;
    private String deliverRequest;
    private String payment;
    private String usepointRequest;
    private int pointNo;
    private String couponRequest;
    private int storeCoupon;
    private int status;
}
