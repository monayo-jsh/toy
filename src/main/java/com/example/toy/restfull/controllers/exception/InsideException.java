package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class InsideException extends CustomException {

    public InsideException() {
        super(ResultCode.INTERNAL_SERVER_ERROR);
    }

    public InsideException(String message) {
        super(ResultCode.INTERNAL_SERVER_ERROR);
        super.addMessage(message);
    }

    public <T> InsideException(T extra) {
        super(ResultCode.INTERNAL_SERVER_ERROR);
        super.addExtra(extra);
    }

}
