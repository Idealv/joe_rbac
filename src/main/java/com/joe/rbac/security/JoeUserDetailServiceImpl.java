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
@Service("joeUserDetailService")
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

        //权限系统中的权限分为三种:页面权限,操作权限,数据权限
        //页面权限:访问当前url所需要的权限(Spring Security配置中的hasRole/hasAuthority)
        //操作权限:执行操作所需要的权限(Controller中方法的@PrePost注解
        //数据权限:限制用户能看到的数据,同时取决于用户的角色和部门(一般在SQL语句中做限制)

        //获取访问菜单所需要的权限
        Set<String> permissions = userService.findPermsByUserId(userId);
        //获取角色的id
        Set<String> roleIds = userService.findRoleIdByUserId(userId);

        permissions.addAll(roleIds);

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));

        UserDetails userDetails= new JoeUserDetails(userId, user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }
}
