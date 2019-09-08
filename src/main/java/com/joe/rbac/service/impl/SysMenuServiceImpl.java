package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysMenu;
import com.joe.rbac.mapper.SysMenuMapper;
import com.joe.rbac.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<String> findPermsByUserId(Integer userId) {
        return baseMapper.findPermsByUserId(userId);
    }
}
