package com.example.toy.configuration.security.handler;

import com.example.toy.domain.ResultCode;
import com.example.toy.domain.ResultVo;
import com.example.toy.restfull.controllers.exception.CustomException;
import com.example.toy.restfull.controllers.exception.InsideException;
import com.example.toy.restfull.controllers.exception.UnauthorizedException;
import com.example.toy.utils.CommonUtil;
import com.example.toy.utils.Consts;
import com.example.toy.utils.ServiceLogger;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        ServiceLogger serviceLogger = (ServiceLogger) request.getAttribute(Consts.REQUEST_ATTRIBUTE_SERVICE_LOGGER);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomException customException = new InsideException(exception.getMessage());
        if (exception.getCause() instanceof CustomException) {
            customException = (CustomException) exception.getCause();
            httpStatus = customException.getHttpStatus();
            serviceLogger.setResult(customException);
        } else {
            serviceLogger.setResult(customException);
        }

        String message = customException.getMessage();
        if(exception.getCause() instanceof UnauthorizedException) {
            //인증 실패한 경우 이유는 로그로 남기고 응답은 인증 실패로
            message = ResultCode.UNAUTHORIZED.getDescription();
        }

        ResultVo resultVo = ResultVo.builder()
                                    .code(customException.getCode())
                                    .message(message)
                                    .build();

        String responseJson = CommonUtil.objectMapper.writeValueAsString(resultVo);

        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter printWriter = response.getWriter();
        printWriter.print(responseJson);
        printWriter.flush();
        printWriter.close();

    }
}
