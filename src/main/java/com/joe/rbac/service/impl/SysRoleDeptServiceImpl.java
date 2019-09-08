package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysRoleDept;
import com.joe.rbac.mapper.SysRoleDeptMapper;
import com.joe.rbac.service.ISysRoleDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {

}
