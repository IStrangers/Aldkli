package com.msw.aldkli.controller

import com.msw.aldkli.meta.ApiGroupMetaData
import com.msw.aldkli.scanner.ApiScanner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aldkli/metaData")
class MetaDataController {

    @GetMapping("getApiGroupMetaDataList")
    fun getApiGroupMetaDataList(scanPackage: String): List<ApiGroupMetaData> = ApiScanner().scan(scanPackage)

}