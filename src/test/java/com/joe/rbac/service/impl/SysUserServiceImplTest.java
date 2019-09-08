package com.joe.rbac.service.impl;

import com.joe.rbac.RbacApplicationTests;
import com.joe.rbac.entity.SysUser;
import com.joe.rbac.service.ISysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class SysUserServiceImplTest extends RbacApplicationTests {
    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void findByUsername() {
        SysUser user = sysUserService.findByUsername("admin");
        assertTrue(user!=null);
    }
}