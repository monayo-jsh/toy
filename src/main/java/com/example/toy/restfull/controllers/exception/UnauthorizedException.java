package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super(ResultCode.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(ResultCode.UNAUTHORIZED);
        super.addMessage(message);
    }

    public <T> UnauthorizedException(T extra) {
        super(ResultCode.UNAUTHORIZED);
        super.addExtra(extra);
    }

}
