package com.joe.rbac.security.utils;

import com.joe.rbac.security.JoeUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHeader}")
    private String accessTokenHeader;



    public JoeUserDetails getUserByToken(HttpServletRequest request) {
        String accessToken = getToken(request.getHeader(this.header));
        if (StringUtils.isNotEmpty(accessToken)){

        }

        return null;
    }

    private String getToken(String accessToken) {
        //token结构: Bearer xxxxxxxxx
        if (StringUtils.isNotEmpty(accessToken)) {
            //截去"Bearer "
            accessToken = accessToken.substring(accessTokenHeader.length());
        }
        return accessToken;
    }
}
