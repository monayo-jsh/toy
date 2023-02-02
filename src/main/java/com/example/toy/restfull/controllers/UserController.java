package com.example.toy.restfull.controllers;

import com.example.toy.restfull.controllers.exception.BadRequestException;
import com.example.toy.domain.ResultVo;
import com.example.toy.domain.user.UserPostReqVo;
import com.example.toy.restfull.services.UserService;
import com.example.toy.utils.CommonUtil;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public @ResponseBody ResultVo user(HttpServletRequest request, @RequestBody @Valid UserPostReqVo userVo, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()) {
            throw new BadRequestException(CommonUtil.makeBindingResultMessage(bindingResult));
        }

        return userService.registerUser(userVo);
    }
}
