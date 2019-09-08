package com.joe.rbac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.joe.rbac.entity.SysUser;
import com.joe.rbac.mapper.SysUserMapper;
import com.joe.rbac.service.ISysMenuService;
import com.joe.rbac.service.ISysUserRoleService;
import com.joe.rbac.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysUserRoleService userRoleService;


    @Override
    public SysUser findByUsername(String username) {
        return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword)
                .eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return menuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService.selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }
}
