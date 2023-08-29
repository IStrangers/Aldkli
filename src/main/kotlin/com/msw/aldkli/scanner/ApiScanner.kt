package com.msw.aldkli.scanner

import com.msw.aldkli.annotation.Api
import com.msw.aldkli.annotation.ApiGroup
import com.msw.aldkli.annotation.ApiParam
import com.msw.aldkli.annotation.ApiParams
import com.msw.aldkli.meta.*
import com.msw.aldkli.util.ClassUtil

import java.lang.reflect.Method
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.lang.reflect.Parameter
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.collections.ArrayList

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
                val (methodType,pathList) = getMethodAndPathList(declaredMethod) ?: continue
                val apiParamMetaDataList = getParameters(declaredMethod)
                val apiReturnTypeMetaData = getReturnType(declaredMethod)
                apiMetaDataList.add(ApiMetaData(api.value,methodType,pathList,apiParamMetaDataList,apiReturnTypeMetaData))
            }
            apiGroupMetaDataList.add(ApiGroupMetaData(apiGroup.value,requestMapping?.value,apiMetaDataList))
        }
        return apiGroupMetaDataList
    }

    private fun getMethodAndPathList(declaredMethod: Method): Pair<MethodType, Array<String>?>? {
        for (annotation in declaredMethod.declaredAnnotations) {
            if (annotation is RequestMapping) {
                return Pair(MethodType.ALL, annotation.value)
            }
            if (annotation is GetMapping) {
                return Pair(MethodType.GET, annotation.value)
            }
            if (annotation is PostMapping) {
                return Pair(MethodType.POST, annotation.value)
            }
            if (annotation is PutMapping) {
                return Pair(MethodType.PUT, annotation.value)
            }
            if (annotation is DeleteMapping) {
                return Pair(MethodType.DELETE, annotation.value)
            }
            if (annotation is PatchMapping) {
                return Pair(MethodType.PATCH, annotation.value)
            }
        }
        return null
    }

    private fun getParameters(declaredMethod: Method): List<ApiParamMetaData>? {
        var apiParamMetaDataList = ArrayList<ApiParamMetaData>()
        val apiParams: ApiParams? = declaredMethod.getDeclaredAnnotation(ApiParams::class.java)
        val parameters = declaredMethod.parameters
        if (apiParams == null) {
            for (parameter in parameters) {
                apiParamMetaDataList.add(toApiParam(null,parameter))
            }
        } else {
            val parameterMap = Arrays.stream(parameters).collect(Collectors.toMap(Parameter::getName, Function.identity()))
            for (apiParam in apiParams.value) {
                val parameter = parameterMap[apiParam.param]
                apiParamMetaDataList.add(toApiParam(apiParam,parameter))
            }
        }
        return apiParamMetaDataList
    }

    private fun toApiParam(apiParam: ApiParam?, parameter: Parameter?): ApiParamMetaData {
        val name = apiParam?.param ?: parameter?.name ?: ""
        val (required,type) = getRequiredAndType(parameter)
        val description = apiParam?.description ?: ""
        val dataType = if(parameter != null) ClassUtil.getGenericTypeName(parameter.parameterizedType) else ""
        val example = apiParam?.example ?: ""
        return ApiParamMetaData(name,required,description,type,dataType,example)
    }

    private fun getRequiredAndType(parameter: Parameter?): Pair<Boolean,String> {
        val requestParam = parameter?.getDeclaredAnnotation(RequestParam::class.java)
        if (requestParam != null) {
            return Pair(requestParam.required,"search")
        }
        val pathVariable = parameter?.getDeclaredAnnotation(PathVariable::class.java)
        if (pathVariable != null) {
            return Pair(pathVariable.required,"urlPath")
        }
        return Pair(false,"")
    }

    private fun getReturnType(declaredMethod: Method): ApiReturnTypeMetaData {
        val genericReturnType = declaredMethod.genericReturnType
        return ApiReturnTypeMetaData("Result",ClassUtil.getGenericTypeName(genericReturnType))
    }

}