package com.joe.rbac.mapper;

import com.joe.rbac.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
//user->role->menu
//用户->角色->权限
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    @Select("select m.perms from sys_menu m, sys_user u, sys_user_role ur, sys_role_menu rm\n" +
            "        where u.user_id = #{user_id} and u.user_id = ur.user_id\n" +
            "          and ur.role_id = rm.role_id and rm.menu_id = m.menu_id")
    List<String> findPermsByUserId(Integer userId);
}
//menu:id name perms
//user:id name
//user_role:id user_id role_id
//role_menu:id role_id menu_id

//menu user user_role role_menu

//user->user_role->role_menu->menu
//根据user_id查找user的role,通过role的id找到role对应的menu

