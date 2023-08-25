package com.msw.aldkli.service.impl

import com.msw.aldkli.AldkliManager
import com.msw.aldkli.meta.ApiEntry
import com.msw.aldkli.service.MetaDataService
import org.springframework.stereotype.Service

@Service
class MetaDataServiceImpl : MetaDataService {

    override fun getApiEntryList(): List<ApiEntry> = AldkliManager.getApiEntryList()

}