package com.joe.rbac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.joe.rbac.constant.JoeConst;
import com.joe.rbac.entity.SysUser;
import com.joe.rbac.exception.BaseException;
import com.joe.rbac.mapper.SysUserMapper;
import com.joe.rbac.service.ISysMenuService;
import com.joe.rbac.service.ISysUserRoleService;
import com.joe.rbac.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @Override
    public String login(String username, String password, String captcha, HttpServletRequest request) {
        Object kaptcha = redisTemplate.opsForValue().get(JoeConst.JOE_IMAGE_SESSION_KEY);

        if (kaptcha==null){
            throw new BaseException("验证码失效");
        }

        if (!captcha.toLowerCase().equals(kaptcha)){
            throw new BaseException("验证码错误");
        }



        return null;
    }
}
