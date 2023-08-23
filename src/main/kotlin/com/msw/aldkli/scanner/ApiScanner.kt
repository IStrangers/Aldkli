package com.msw.aldkli.scanner

import com.msw.aldkli.annotation.Api
import com.msw.aldkli.annotation.ApiGroup
import com.msw.aldkli.meta.ApiGroupMetaData
import com.msw.aldkli.meta.ApiMetaData
import com.msw.aldkli.util.ClassUtil

class ApiScanner {

    fun scan(scanPackage: String): List<ApiGroupMetaData>  {
        var apiGroupClassList = ClassUtil.scanClass(scanPackage) { kClass ->
            kClass.annotations.find { annotation -> annotation is ApiGroup } != null
        }
        var apiGroupMetaDataList = ArrayList<ApiGroupMetaData>(apiGroupClassList.size)
        for (apiGroupClass in apiGroupClassList) {
            val javaClass = apiGroupClass.java
            val apiGroup = javaClass.getDeclaredAnnotation(ApiGroup::class.java)
            val declaredMethods = javaClass.declaredMethods
            val apiMetaDataList = ArrayList<ApiMetaData>()
            for (declaredMethod in declaredMethods) {
                val api = declaredMethod.getDeclaredAnnotation(Api::class.java) ?: continue
                apiMetaDataList.add(ApiMetaData(api.value))
            }
            apiGroupMetaDataList.add(ApiGroupMetaData(apiGroup.value,apiMetaDataList))
        }
        return apiGroupMetaDataList
    }

}