package com.joe.rbac.security.utils;

import com.alibaba.fastjson.JSON;
import com.joe.rbac.common.ResponseCode;
import com.joe.rbac.common.ServerResponse;
import com.joe.rbac.exception.BaseException;
import com.joe.rbac.security.JoeUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@UtilityClass
public class SecurityUtil {

    public static void response(HttpStatus httpStatus,ServerResponse serverResponse, HttpServletResponse response) throws IOException {
        response.setStatus(httpStatus.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(serverResponse));
        writer.flush();
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public JoeUserDetails getUser() {
        try {
            return (JoeUserDetails) getAuthentication().getPrincipal();
        } catch (Exception e){
            throw new BaseException("登录状态过期", HttpStatus.UNAUTHORIZED.value());
        }

    }
}
