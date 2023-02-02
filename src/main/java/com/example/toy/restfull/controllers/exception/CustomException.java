package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;
import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private final String code;
    private String message;
    private Object extra;

    public CustomException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDescription();
    }

    public CustomException(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public <T> void addExtra(T extra) {
        if(extra instanceof String) {
            this.message = String.format("%s - %s", this.message, extra);
            return;
        }

        this.extra = extra;
    }
}
