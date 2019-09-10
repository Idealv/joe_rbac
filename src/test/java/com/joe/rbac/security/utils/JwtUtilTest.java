package com.joe.rbac.security.utils;

import com.joe.rbac.RbacApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class JwtUtilTest extends RbacApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getUserByToken() {
        jwtUtil.getUserByToken();
    }
}