package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;
import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private final String code;
    private final String message;
    private Object extra;

    public CustomException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDescription();
    }

    public <T> void addExtra(T extra) {
        this.extra = extra;
    }
}
