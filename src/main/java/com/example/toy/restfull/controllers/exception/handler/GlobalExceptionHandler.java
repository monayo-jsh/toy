package com.example.toy.restfull.controllers.exception.handler;

import com.example.toy.restfull.controllers.exception.BadRequestException;
import com.example.toy.domain.ResultVo;
import com.example.toy.restfull.controllers.exception.DatabaseException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResultVo handlerBadRequestException(HttpServletRequest request, BadRequestException ex) {
        return new ResultVo(ex);
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResultVo handlerDatabaseException(HttpServletRequest request, DatabaseException ex) {
        ex.printStackTrace();

        return new ResultVo(ex);
    }
}
