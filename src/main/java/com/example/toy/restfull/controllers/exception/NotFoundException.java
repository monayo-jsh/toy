package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class NotFoundException extends CustomException {

    public NotFoundException() {
        super(ResultCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ResultCode.NOT_FOUND);
        super.addMessage(message);
    }

    public <T> NotFoundException(T extra) {
        super(ResultCode.NOT_FOUND);
        super.addExtra(extra);
    }

}
