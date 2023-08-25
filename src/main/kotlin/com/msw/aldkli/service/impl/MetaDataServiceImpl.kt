package com.msw.aldkli.service.impl

import com.msw.aldkli.AldkliContext
import com.msw.aldkli.meta.ApiEntry
import com.msw.aldkli.service.MetaDataService
import org.springframework.stereotype.Service

@Service
class MetaDataServiceImpl(private val aldkliContext: AldkliContext) : MetaDataService {

    override fun getApiEntryList(): List<ApiEntry> = aldkliContext.getApiEntryList()

}