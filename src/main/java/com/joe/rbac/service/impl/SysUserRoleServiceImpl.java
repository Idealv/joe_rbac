package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysUserRole;
import com.joe.rbac.mapper.SysUserRoleMapper;
import com.joe.rbac.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public List<SysUserRole> selectUserRoleListByUserId(Integer userId) {
        return baseMapper.selectUserRoleListByUserId(userId);
    }
}
