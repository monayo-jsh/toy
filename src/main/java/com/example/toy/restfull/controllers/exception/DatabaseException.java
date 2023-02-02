package com.example.toy.restfull.controllers.exception;

import com.example.toy.domain.ResultCode;

public class DatabaseException extends CustomException {

    public DatabaseException() {
        super(ResultCode.INTERNAL_SERVER_ERROR);
    }
    public DatabaseException(ResultCode resultCode) { super(resultCode); }

    public <T> DatabaseException(ResultCode resultCode, T extra) {
        super(resultCode);
        super.addExtra(extra);
    }

}
