package com.example.toy.restfull.controllers.exception.database;

import com.example.toy.domain.ResultCode;
import com.example.toy.restfull.controllers.exception.DatabaseException;

public class DatabaseReadException extends DatabaseException {

    public DatabaseReadException() { super(ResultCode.DB_SELECT_ERROR); }

    public <T> DatabaseReadException(T extra) {
        super(ResultCode.DB_SELECT_ERROR, extra);
    }

}
