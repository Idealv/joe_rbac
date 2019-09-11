package com.joe.rbac.controller;

import com.joe.rbac.common.ServerResponse;
import com.joe.rbac.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    private ISysUserService userService;

    @GetMapping("/login")
    public ServerResponse<String> login(String username,String password,String captcha){
        return ServerResponse.createBySuccess(userService.login(username, password, captcha));
    }

}
