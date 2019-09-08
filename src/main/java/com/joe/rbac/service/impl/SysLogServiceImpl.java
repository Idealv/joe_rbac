package com.joe.rbac.service.impl;

import com.joe.rbac.entity.SysLog;
import com.joe.rbac.mapper.SysLogMapper;
import com.joe.rbac.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
