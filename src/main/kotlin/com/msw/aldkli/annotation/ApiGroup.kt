package com.msw.aldkli.annotation

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiGroup(val value: String)
