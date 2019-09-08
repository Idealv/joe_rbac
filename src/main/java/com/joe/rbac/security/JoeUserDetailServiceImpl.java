package com.joe.rbac.security;

import com.joe.rbac.entity.SysUser;
import com.joe.rbac.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class JoeUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByUsername(username);

        if (Objects.equals(user,null)){
            String msg = "登录用户: " + user.getUsername() + "不存在";
            log.error(msg);
            throw new UsernameNotFoundException(msg);
        }

        Integer userId = user.getUserId();

        Set<String> permissions = userService.findPermsByUserId(userId);
        Set<String> roleIds = userService.findRoleIdByUserId(userId);

        permissions.addAll(roleIds);

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));

        return new JoeUserDetails(userId, user.getUsername(), user.getPassword(), authorities);
    }
}
