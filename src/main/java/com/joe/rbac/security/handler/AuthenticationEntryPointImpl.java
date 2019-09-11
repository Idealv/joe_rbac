package com.joe.rbac.security.handler;

import com.joe.rbac.common.ServerResponse;
import com.joe.rbac.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("authenticationEntryPoint")
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String info = "请求访问: " + request.getRequestURI() + "经过jwt认证失败,无法获取系统资源";
        log.error(info);
        log.error(e.getMessage());
        SecurityUtil.response(HttpStatus.UNAUTHORIZED,
                ServerResponse.createByErrorMessage(info), response);
    }
}
