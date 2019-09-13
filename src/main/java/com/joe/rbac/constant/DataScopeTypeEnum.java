package com.joe.rbac.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {
    ALL(1,"全部"),

    THIS_LEVEL(2,"本级"),

    THIS_LEVEL_CHILDREN(3,"本级以及子级"),

    CUSTOMIZE(4,"自定义")

    ;

    private int type;

    private String description;

    public static DataScopeTypeEnum valueOf(int type){
        for (DataScopeTypeEnum dataScopeTypeEnum:
             DataScopeTypeEnum.values()) {
            if (type == dataScopeTypeEnum.getType()) {
                return dataScopeTypeEnum;
            }
        }
        //默认只能查看本级
        return THIS_LEVEL;
    }
}
