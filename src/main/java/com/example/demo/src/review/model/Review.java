package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private int review_no;
    private int user_no;
    private int store_no;
    private float star;
    private String contents;
    private String image;
    private Date updatedAt;
}
