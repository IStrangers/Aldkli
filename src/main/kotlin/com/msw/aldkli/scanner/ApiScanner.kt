package com.msw.aldkli.scanner

import com.msw.aldkli.annotation.Api
import com.msw.aldkli.annotation.ApiGroup
import com.msw.aldkli.meta.ApiGroupMetaData
import com.msw.aldkli.meta.ApiMetaData
import com.msw.aldkli.meta.MethodType
import com.msw.aldkli.util.ClassUtil

import java.lang.reflect.Method
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping

class ApiScanner {

    fun scan(scanPackage: String): List<ApiGroupMetaData>  {
        var apiGroupClassList = ClassUtil.scanClass(scanPackage) { kClass ->
            kClass.annotations.find { annotation -> annotation is ApiGroup } != null
        }

        var apiGroupMetaDataList = ArrayList<ApiGroupMetaData>(apiGroupClassList.size)
        for (apiGroupClass in apiGroupClassList) {
            val javaClass = apiGroupClass.java
            val apiGroup = javaClass.getDeclaredAnnotation(ApiGroup::class.java)
            val requestMapping = javaClass.getDeclaredAnnotation(RequestMapping::class.java)
            val declaredMethods = javaClass.declaredMethods
            val apiMetaDataList = ArrayList<ApiMetaData>()
            for (declaredMethod in declaredMethods) {
                val api = declaredMethod.getDeclaredAnnotation(Api::class.java) ?: continue
                val (methodType,pathList) = getMappingAnnotationParams(declaredMethod) ?: continue
                apiMetaDataList.add(ApiMetaData(api.value,methodType,pathList))
            }
            apiGroupMetaDataList.add(ApiGroupMetaData(apiGroup.value,requestMapping?.value,apiMetaDataList))
        }
        return apiGroupMetaDataList
    }

    private fun getMappingAnnotationParams(declaredMethod: Method?): Pair<MethodType, Array<String>?>? {
        for (annotation in declaredMethod?.declaredAnnotations!!) {
            if (annotation is RequestMapping) {
                return Pair(MethodType.ALL,annotation?.value)
            }
            if (annotation is GetMapping) {
                return Pair(MethodType.GET,annotation?.value)
            }
            if (annotation is PostMapping) {
                return Pair(MethodType.POST,annotation?.value)
            }
            if (annotation is PutMapping) {
                return Pair(MethodType.PUT,annotation?.value)
            }
            if (annotation is DeleteMapping) {
                return Pair(MethodType.DELETE,annotation?.value)
            }
            if (annotation is PatchMapping) {
                return Pair(MethodType.PATCH,annotation?.value)
            }
        }
        return null
    }

}