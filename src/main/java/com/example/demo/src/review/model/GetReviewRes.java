package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private Integer reviewNo;
    private int userNo;
    private int storeNo;
    private float star;
    private String contents;
    private String image;
    private Date updatedAt;
}
