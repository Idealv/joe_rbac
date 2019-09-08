package com.joe.rbac.mapper;

import com.joe.rbac.RbacApplicationTests;
import com.joe.rbac.entity.SysDept;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SysDeptMapperTest extends RbacApplicationTests {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Test
    public void testMapper(){
        List<SysDept> depts = sysDeptMapper.selectList(null);
        assertTrue(depts.size() > 0);
    }
}