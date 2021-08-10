package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private int orderNo;
    private int basketNo;
    private String storeRequest;
    private String deliverRequest;
    private String payment;
    private String usepointRequest;
    private int pointNo;
    private String couponRequest;
    private int storeCoupon;
}
