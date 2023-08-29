package com.msw.aldkli.meta

data class ApiMetaData(
    val name: String,
    val methodType: MethodType,
    val pathList: Array<String>?,
    val apiParamMetaDataList: List<ApiParamMetaData>?,
    val apiReturnTypeMetaData: ApiReturnTypeMetaData
)
