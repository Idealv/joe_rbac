package com.joe.rbac.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping
    public String list(){
        return "There is user view";
        //return new ResponseEntity<>()
    }
}

