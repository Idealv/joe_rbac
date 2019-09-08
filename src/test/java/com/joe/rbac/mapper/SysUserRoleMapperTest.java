package com.joe.rbac.mapper;

import com.joe.rbac.RbacApplicationTests;
import com.joe.rbac.entity.SysUserRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SysUserRoleMapperTest extends RbacApplicationTests {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Test
    public void selectUserRoleListByUserId() {
        List<SysUserRole> userRoleList = userRoleMapper.selectUserRoleListByUserId(4);
        assertTrue(userRoleList.size()>0);
    }
}