package com.joe.rbac.security.utils;

import com.joe.rbac.security.JoeUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleAction;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@Slf4j
public class JwtUtil {

    private final String USERNAME = Claims.SUBJECT;

    private final String USERID="userid";

    private final String CREATED = "created";

    private final String AUTHORITIES = "authorities";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHeader}")
    private String accessTokenHeader;

    @Value("${jwt.expiration}")
    private long expiration;

    //根据传入的用户信息生成token
    public String generateToken(JoeUserDetails userDetails){
        Map<String, Object> claims = new HashMap<>(4);
        //sub:userDetails.username
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(USERID, userDetails.getUserId());
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, userDetails.getAuthorities());
        return generateToken(claims);
    }


    private String generateToken(Map<String,Object> claims){
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

//    public JoeUserDetails getUserByToken1(String accessToken){
//        String token = accessToken;
//
//        if (StringUtils.isNotEmpty(token)){
//            Claims claims = getClaimsFromToken(token);
//
//            if (claims==null) return null;
//
//            String username = claims.getSubject();
//
//            if (username==null||isTokenExpired(token))
//                return null;
//
//            Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
//            Integer userId = (Integer) claims.get(USERID);
//
//            if (validateToken(username,token))
//                return new JoeUserDetails(userId, username, "", authorities);
//
//        }
//
//        return null;
//    }


    //将token解析为userDetails的实现类
    public JoeUserDetails getUserByToken(HttpServletRequest request) {
        String token = getToken(request);
        //String token = accessToken;

        if (StringUtils.isNotEmpty(token)){
            Claims claims = getClaimsFromToken(token);

            if (claims==null) return null;

            String username = claims.getSubject();

            if (username==null||isTokenExpired(token))
                return null;

            Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
            Integer userId = (Integer) claims.get(USERID);

            if (validateToken(username,token))
                return new JoeUserDetails(userId, username, "", authorities);

        }

        return null;
    }

    //截取accessToken中的正文
    private String getToken(HttpServletRequest request) {
        String accessToken = request.getHeader(header);
        //token结构: Bearer xxxxxxxxx
        if (StringUtils.isNotEmpty(accessToken)) {
            //截去"Bearer "
            accessToken = accessToken.substring(accessTokenHeader.length());
        }
        return accessToken;
    }

    //通过token获取username
    public String getUsernameFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    //通过token获取Claims对象
    private Claims getClaimsFromToken(String token){
        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            claims = null;
            //todo Throw getClaimsError Exception
        }

        return claims;
    }

    //判断token是否过期
    private Boolean isTokenExpired(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private Boolean validateToken(String username,String token){
        //todo 优化操作，此时会解析两次token
        String usernameInToken = getUsernameFromToken(token);
        return StringUtils.equals(username, usernameInToken) && !isTokenExpired(token);
    }

    //刷新token
    public String refreshToken(String token){
        String refreshToken;

        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshToken = generateToken(claims);
        }catch (Exception e){
            refreshToken = null;
        }

        return refreshToken;
    }
}
