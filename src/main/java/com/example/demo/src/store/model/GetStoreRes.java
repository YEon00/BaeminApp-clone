package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Data
public class GetStoreRes {
    private int storeNo;
    private int categoryNo;
    private String intro;
    private String phone;
    private int deliverTime;
    private String hygieneInfo;
    private String name;
    private int minPrice;
    private int isPaymentNow;
    private int isPaymentMeet;
    private Date updatedAt;
}
