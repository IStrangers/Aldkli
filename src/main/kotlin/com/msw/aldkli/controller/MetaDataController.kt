package com.msw.aldkli.controller

import com.msw.aldkli.meta.ApiEntry
import com.msw.aldkli.service.MetaDataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("aldkli/metaData")
class MetaDataController {

    constructor(metaDataService: MetaDataService) {
        this.metaDataService = metaDataService
    }

    private var metaDataService: MetaDataService

    @GetMapping("getApiEntryList")
    fun getApiEntryList(): List<ApiEntry> = metaDataService.getApiEntryList()

}