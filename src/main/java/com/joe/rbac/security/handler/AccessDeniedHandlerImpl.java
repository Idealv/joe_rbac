package com.joe.rbac.security.handler;

import com.joe.rbac.common.ServerResponse;
import com.joe.rbac.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

//权限不足，返回403
@Slf4j
@Component("accessDeniedHandler")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String info = "请求: " + request.getRequestURI() + "权限不足,无法获取资源";
        log.info(info);
        SecurityUtil.response(HttpStatus.FORBIDDEN,
                ServerResponse.createByErrorMessage(info), response);
    }
}
