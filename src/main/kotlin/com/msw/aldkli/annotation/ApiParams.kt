package com.msw.aldkli.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiParams(val value: Array<ApiParam>)
