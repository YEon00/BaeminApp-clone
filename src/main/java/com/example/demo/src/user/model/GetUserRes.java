package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int userNo;
    private String name;
    private String id;
    private String password;
    private String email;
    private String nickname;
    private String address;
    private String phone;
    private String image;
    private String status;

}
