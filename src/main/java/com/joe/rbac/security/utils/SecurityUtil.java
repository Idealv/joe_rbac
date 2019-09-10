package com.joe.rbac.security.utils;

import com.alibaba.fastjson.JSON;
import com.joe.rbac.common.ResponseCode;
import com.joe.rbac.common.ServerResponse;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityUtil {

    public static void response(HttpStatus httpStatus,ServerResponse serverResponse, HttpServletResponse response) throws IOException {
        response.setStatus(httpStatus.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(serverResponse));
        writer.flush();
    }
}
