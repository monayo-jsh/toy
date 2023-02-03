package com.example.toy.restfull.controllers.exception.handler;

import com.example.toy.restfull.controllers.exception.BadRequestException;
import com.example.toy.domain.ResultVo;
import com.example.toy.restfull.controllers.exception.CustomException;
import com.example.toy.restfull.controllers.exception.database.DatabaseException;
import com.example.toy.utils.Consts;
import com.example.toy.utils.ServiceLogger;
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
        setServiceLoggerResult(request, ex);
        return new ResultVo(ex);
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResultVo handlerDatabaseException(HttpServletRequest request, DatabaseException ex) {
        ex.printStackTrace();

        setServiceLoggerResult(request, ex);
        return new ResultVo(ex);
    }

    private void setServiceLoggerResult(HttpServletRequest request, CustomException ex) {
        ServiceLogger serviceLogger = (ServiceLogger) request.getAttribute(Consts.REQUEST_ATTRIBUTE_SERVICE_LOGGER);
        serviceLogger.setResult(ex);
    }
}
