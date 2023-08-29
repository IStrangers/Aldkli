package com.aix.entity;


import com.msw.aldkli.annotation.ApiEntity;
import com.msw.aldkli.annotation.ApiProperty;

@ApiEntity("部门")
public class Dept {

    @ApiProperty("部门名称")
    private String deptName;
    @ApiProperty("部门编码")
    private String code;

}
