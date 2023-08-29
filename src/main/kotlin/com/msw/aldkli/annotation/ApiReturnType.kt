package com.msw.aldkli.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiReturnType(val name: String,val description: String,val dataType: String,val children: Array<ApiReturnType> = [])
