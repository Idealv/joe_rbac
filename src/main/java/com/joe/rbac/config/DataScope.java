package com.joe.rbac.config;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {

    /**
     * 限制的数据范围
     */
    private String scopeName = "deptId";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds = new ArrayList<>();


    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;

}
