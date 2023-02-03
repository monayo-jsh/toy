package com.example.toy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResultCode {
    OK(HttpStatus.OK, "200000", "성공"),
    OK_ALREADY(HttpStatus.OK, "200001", "이미 처리됨"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400000", "Bad Request, 클라이언트의 잘못된 요청으로 처리할 수 없음"),
    INVALID_HEADER(HttpStatus.BAD_REQUEST, "400001", "유효하지 않은 Header"),
    INVALID_PARAM(HttpStatus.BAD_REQUEST, "400002", "유효하지 않은 Parameter"),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401000", "인증 실패"),
    NO_PERMISSION(HttpStatus.UNAUTHORIZED, "401001", "인증 실패 - 토큰 만료"),

    FORBIDDEN(HttpStatus.FORBIDDEN, "403000", "권한 없음"),

    NOT_FOUND(HttpStatus.NOT_FOUND, "404000", "정보 없음"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "405000", "서버에 요청을 수행할 수 있는 기능 없음(METHOD)"),

    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "415000", "Content-Type 유효하지 않음(application/json만 허용)"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500000", "internal exception"),
    MAINTENANCE(HttpStatus.INTERNAL_SERVER_ERROR, "500001", "작업 공지"),
    DB_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500100", "Database 연결 실패"),
    DB_SELECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500101", "Database select 실패"),
    DB_INSERT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500102", "Database insert 실패"),
    DB_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500103", "Database update 실패"),
    DB_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500104", "Database delete 실패");

    private final HttpStatus httpStatus;
    private final String code;
    private final String description;
}
