package com.joe.rbac.service;

import com.joe.rbac.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<String> findPermsByUserId(Integer userId);
}
