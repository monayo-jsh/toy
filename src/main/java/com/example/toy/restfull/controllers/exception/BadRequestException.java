package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class BadRequestException extends CustomException {

    public BadRequestException() {
        super(ResultCode.BAD_REQUEST);
    }

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public <T> BadRequestException(T extra) {
        super(ResultCode.BAD_REQUEST);
        super.addExtra(extra);
    }

}
