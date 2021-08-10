package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchStoreNoticeReq {
    private int storeNo;
    private String noticeImage;
    private String noticeContents;
}
