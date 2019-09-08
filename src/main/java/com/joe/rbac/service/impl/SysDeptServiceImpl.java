package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysDept;
import com.joe.rbac.mapper.SysDeptMapper;
import com.joe.rbac.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

}
