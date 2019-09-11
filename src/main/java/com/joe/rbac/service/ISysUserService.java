package com.joe.rbac.service;

import com.joe.rbac.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

    Set<String> findPermsByUserId(Integer userId);

    Set<String> findRoleIdByUserId(Integer userId);

    String login(String username, String password, String captcha);

}
