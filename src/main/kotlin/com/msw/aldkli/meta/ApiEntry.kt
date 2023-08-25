package com.msw.aldkli.meta

data class ApiEntry(val name: String, val scanPackagePath: String) {

    private lateinit var apiGroupMetaDataList: List<ApiGroupMetaData>

    fun setApiGroupMetaDataList(apiGroupMetaDataList: List<ApiGroupMetaData>) {
        this.apiGroupMetaDataList = apiGroupMetaDataList
    }

    fun getApiGroupMetaDataList() = this.apiGroupMetaDataList

}

