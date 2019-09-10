package com.joe.rbac.security;

import com.joe.rbac.RbacApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.Assert.*;

public class JoeUserDetailServiceImplTest extends RbacApplicationTests {
    @Autowired
    private UserDetailsService joeUserDetailService;

    @Test
    public void loadUserByUsername() {
        UserDetails user = joeUserDetailService.loadUserByUsername("admin");
        assertTrue(user != null);
    }
}