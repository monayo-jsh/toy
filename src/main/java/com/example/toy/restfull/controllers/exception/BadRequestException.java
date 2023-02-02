package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class BadRequestException extends CustomException {

    public BadRequestException() {
        super(ResultCode.BAD_REQUEST);
    }

    public <T> BadRequestException(T extra) {
        super(ResultCode.BAD_REQUEST);
        super.addExtra(extra);
    }

}
