package com.msw.aldkli.service

import com.msw.aldkli.meta.ApiEntry

interface MetaDataService {

    fun getApiEntryList(): List<ApiEntry>

}