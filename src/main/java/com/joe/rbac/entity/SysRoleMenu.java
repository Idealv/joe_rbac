package com.joe.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author joe
 * @since 2019-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;


}
