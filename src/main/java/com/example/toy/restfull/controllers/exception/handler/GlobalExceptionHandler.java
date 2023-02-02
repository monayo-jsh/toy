package com.example.toy.restfull.controllers.exception.handler;

import com.example.toy.restfull.controllers.exception.BadRequestException;
import com.example.toy.domain.ResultVo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResultVo handlerBadRequestException(HttpServletRequest request, BadRequestException ex) {
        return new ResultVo(ex);
    }
}
