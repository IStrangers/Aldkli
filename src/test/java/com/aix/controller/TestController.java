package com.aix.controller;

import com.aix.entity.User;
import com.msw.aldkli.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiGroup("测试分组")
@RequestMapping("test")
public class TestController {

    @Api("获取测试列表")
    @RequestMapping({"getTestList","TestList"})
    @ApiParams({
        @ApiParam(param = "param1",description = "参数1",example = "test"),
        @ApiParam(param = "param2",example = "1"),
    })
    @ApiReturnType(name = "Result",description = "测试列表",dataType = "List<Map<String,Object>>",children = {
        @ApiReturnType(name = "testName",description = "测试名称",dataType = "String"),
        @ApiReturnType(name = "testCode",description = "测试编码",dataType = "String"),
        @ApiReturnType(name = "testMap",description = "测试映射",dataType = "Map<String,Object>",children = {
            @ApiReturnType(name = "mappingName",description = "映射名称",dataType = "String"),
            @ApiReturnType(name = "mappingCode",description = "映射编码",dataType = "String")
        })
    })
    public List<Map<String, Object>> getTestList(@RequestParam(required = false) String param1, @PathVariable int param2) {
        return new ArrayList<>();
    }

    @Api("获取用户列表")
    @GetMapping("getUserList")
    public List<User> getUserList(Map<String,Long> data) {
        return new ArrayList<>();
    }

    @Api("获取用户")
    @GetMapping("getUser")
    public User getUser() {
        return new User();
    }

}
