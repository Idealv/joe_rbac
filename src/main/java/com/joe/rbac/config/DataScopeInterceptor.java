package com.joe.rbac.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.joe.rbac.constant.DataScopeTypeEnum;
import com.joe.rbac.exception.BaseException;
import com.joe.rbac.security.JoeUserDetails;
import com.joe.rbac.security.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@AllArgsConstructor
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {

    private final DataSource dataSource;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作 不是直接过滤
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        // 执行的SQL语句
        String originalSql = boundSql.getSql();
        // SQL语句的参数
        Object parameterObject = boundSql.getParameterObject();
        DataScope dataScope = getDataScope(parameterObject);

        if (Objects.isNull(dataScope))
            return invocation.proceed();

        String scopeName = dataScope.getScopeName();
        List<Integer> deptIds = dataScope.getDeptIds();

        if (deptIds.isEmpty()){
            JoeUserDetails user = SecurityUtil.getUser();

            if (user==null)
                throw new BaseException("auto check dataScope,please ensure have authentication info");

            //解析roleId
            List<String> roleIdList = user.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .filter(authority -> authority.startsWith("ROLE_"))
                    .map(authority -> authority.split("_")[1])
                    .collect(Collectors.toList());

            //通过roleId查看数据权限
            Entity query = Db.use(dataSource)
                    .query("SELECT * FROM sys_role WHERE role_id IN (" + CollUtil.join(roleIdList,",") +")")
                    .stream()
                    .min(Comparator.comparingInt(o -> o.getInt("ds_type")))
                    .get();

            Integer dsType = query.getInt("ds_type");

            if (DataScopeTypeEnum.ALL.getType()==dsType)
                return invocation.proceed();

            String dsScope = query.getStr("ds_scope");

            deptIds.addAll(Arrays.stream(dsScope.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList()));

            String join = CollUtil.join(roleIdList, ",");

            originalSql = "SELECT * FROM (" + originalSql + ") temp_data_scope WHERE temp_data_scope." + scopeName + " IN (" + join + ")";

            metaObject.setValue("delegate.boundSql.sql", originalSql);
            return invocation.proceed();
        }


        return invocation.proceed();
    }

    private DataScope getDataScope(Object parameterObject){
        if (parameterObject instanceof DataScope){
            return (DataScope) parameterObject;
        }else if (parameterObject instanceof Map){
            for (Object val:
                    ((Map<?,?>) parameterObject).values()) {
                if (val instanceof DataScope){
                    return (DataScope) val;
                }
            }
        }

        return null;
    }
}
