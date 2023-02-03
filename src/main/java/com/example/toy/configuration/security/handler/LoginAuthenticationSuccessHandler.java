package com.example.toy.configuration.security.handler;

import com.example.toy.domain.ResultCode;
import com.example.toy.domain.ResultVo;
import com.example.toy.utils.CommonUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", "dashboard");

        ResultVo resultVo = ResultVo.builder()
                                    .code(ResultCode.OK.getCode())
                                    .message(ResultCode.OK.getDescription())
                                    .result(jsonObject)
                                    .build();

        String responseJson = CommonUtil.objectMapper.writeValueAsString(resultVo);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(responseJson);
        printWriter.flush();
        printWriter.close();
    }
}
