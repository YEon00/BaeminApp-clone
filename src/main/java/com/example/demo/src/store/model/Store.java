package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class Store {

    private int store_no;
    private int category_no;
    private String id;
    private String name;
    private String intro;
    private String phone;
    private String deliver_time;
    private String bossname;
    private String store_address;
    private String store_Rnumber;
    private int isTogo;
    private int isStore;
    private int min_price;
    private String hygiene_info;

    private int isPayment_now;
    private int isPayment_meet;
    private String main_image;
    private String notice_image;
    private String notice_contents;
    //
}