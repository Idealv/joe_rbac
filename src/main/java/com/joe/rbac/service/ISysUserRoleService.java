package com.joe.rbac.service;

import com.joe.rbac.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);

}
