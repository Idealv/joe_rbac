package com.joe.rbac.security.utils;

import com.joe.rbac.RbacApplicationTests;
import com.joe.rbac.security.JoeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

import static org.junit.Assert.*;

public class JwtUtilTest extends RbacApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @Before
    public void testGenerateToken() throws Exception{
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");
        JoeUserDetails userDetails = new JoeUserDetails(1, "xiaoming", "123456", authorities);
        token = jwtUtil.generateToken(userDetails);
    }

    @Test
    public void testGetUsernameFromToken() throws Exception{
        String username = jwtUtil.getUsernameFromToken(token);
        assertTrue(username != null);
    }

    @Test
    public void testGetUserByToken(){
        //JoeUserDetails userDetails = jwtUtil.getUserByToken(token);
        //assertTrue(userDetails != null);
    }


}