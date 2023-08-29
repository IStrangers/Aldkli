package com.aix.controller;

import com.msw.aldkli.annotation.Api;
import com.msw.aldkli.annotation.ApiGroup;
import com.msw.aldkli.annotation.ApiParam;
import com.msw.aldkli.annotation.ApiParams;
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
    public List<Map<String, Object>> getTestList(@RequestParam(required = false) String param1, @PathVariable int param2) {
        return new ArrayList<>();
    }

    @Api("getMapping")
    @GetMapping("getMapping")
    public Object getMapping(Map<String,Long> data) {
        return new ArrayList<>();
    }

}
