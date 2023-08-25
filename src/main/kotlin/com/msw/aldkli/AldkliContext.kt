package com.msw.aldkli

import com.msw.aldkli.meta.ApiEntry

class AldkliContext {

    private val apiEntryList = ArrayList<ApiEntry>()

    fun addApiEntry(apiEntry: ApiEntry) {
        apiEntryList.add(apiEntry)
    }

    fun getApiEntryList() = apiEntryList

}