package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    //
    EMPTY_TOKEN(false,2005,"토큰이 존재하지 않습니다"),
    WRONG_URL(false,2006,"잘못된 url"),
    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    USERS_EMPTY_PASSWORD(false, 2010, "패스워드를 확인해주세요."),
    USERS_EMPTY_PHONE(false, 2010, "핸드폰 번호를 확인해주세요."),


    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_EXISTS_Id(false,2018,"중복된 아이디입니다."),

    // [POST] /stores
    POST_STORES_EMPTY_NAME(false, 2020, "가게 이름을 입력해주세요."),
    POST_STORES_EMPTY_ID(false, 2021, "가게 아이디를 입력해주세요."),
    POST_STORES_EMPTY_Rnumber(false, 2022, "사업자 등록번호를 입력해주세요."),
    POST_STORES_EMPTY_INTRO(false, 2023, "가게 설명을 입력해주세요."),
    POST_STORES_EXISTS_ID(false, 2024, "동일한 가게 아이디가 존재합니다"),
    POST_STORES_EXISTS_Rnumber(false, 2025, "동일한 사업자 등록번호가 존재합니다"),

    // [POST] /reviews
    POST_STORES_REVIEW_USER(false, 2024, "유저 정보를 입력해주세요"),
    POST_STORES_REVIEW_CONTENTS(false, 2025, "내용을 입력해주세요."),
    POST_STORES_REVIEW_STAR(false, 2026, "별점을 입력해주세요."),

    // [POST] /orders
    POST_ORDER_EMPTY_BASKET(false, 2040, "장바구니가 비어있습니다"),
    POST_ORDER_EMPTY_PAYMENT(false, 2041, "결제정보를 입력해주세요."),







    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),
    FAILED_TO_CONNECT(false,3015,"커넥션 실패"),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    DELETE_FAIL_USER(false,4011,"유저삭제실패"),

    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),
    DELETE_FAIL_STORE(false,4015,"가게 삭제 실패"),

    MODIFY_FAIL_NOTICE(false,40101,"공지사항 수정 실패"),
    MODIFY_FAIL_STORENAME(false,4100, "가게 이름 수정 실패"),
    MODIFY_FAIL_ORDER(false,4101,"주문 수정 실패"),
    MODIFY_FAIL_REVIEW(false,4102,"리뷰 수정 실패"),
    DELETE_FAIL_ORDER(false,4103,"주문 삭제 실패"),
    DELETE_FAIL_REVIEW(false,4104,"리뷰 삭제 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
