package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysUser;
import com.joe.rbac.mapper.SysUserMapper;
import com.joe.rbac.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
