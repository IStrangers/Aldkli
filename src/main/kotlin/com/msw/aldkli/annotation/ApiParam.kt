package com.msw.aldkli.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiParam(val param: String,val description: String = "",val example: String = "")
