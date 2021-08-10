package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
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
    private Date updatedAt;

}
