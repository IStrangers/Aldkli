package com.aix.controller;

import com.msw.aldkli.annotation.Api;
import com.msw.aldkli.annotation.ApiGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiGroup("测试分组")
public class TestController {

    @Api("获取测试列表")
    public List<Map<String, Object>> getTestList() {
        return new ArrayList<>();
    }

}
