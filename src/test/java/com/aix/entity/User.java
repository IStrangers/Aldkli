package com.aix.entity;

import com.msw.aldkli.annotation.ApiEntity;
import com.msw.aldkli.annotation.ApiProperty;

@ApiEntity("用户")
public class User {

    @ApiProperty("用户名称")
    private String userName;
    @ApiProperty("编码")
    private Long code;
    @ApiProperty("用户所在部门")
    private Dept dept;

}
