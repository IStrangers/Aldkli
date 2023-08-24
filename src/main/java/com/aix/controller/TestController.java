package com.aix.controller;

import com.msw.aldkli.annotation.Api;
import com.msw.aldkli.annotation.ApiGroup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiGroup("测试分组")
@RequestMapping("test")
public class TestController {

    @Api("获取测试列表")
    @RequestMapping({"getTestList","TestList"})
    public List<Map<String, Object>> getTestList() {
        return new ArrayList<>();
    }

    @Api("getMapping")
    @GetMapping("getMapping")
    public Object getMapping() {
        return new ArrayList<>();
    }

}
