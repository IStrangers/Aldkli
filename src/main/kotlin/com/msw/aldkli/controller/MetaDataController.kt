package com.msw.aldkli.controller

import com.msw.aldkli.meta.ApiGroupMetaData
import com.msw.aldkli.scanner.ApiScanner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("aldkli/metaData")
class MetaDataController {

    @GetMapping("getApiGroupMetaDataList/{scanPackage}")
    fun getApiGroupMetaDataList(@PathVariable scanPackage: String): List<ApiGroupMetaData> = ApiScanner().scan(scanPackage)

}