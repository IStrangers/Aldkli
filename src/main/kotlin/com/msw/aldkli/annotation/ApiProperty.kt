package com.msw.aldkli.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiProperty(val value: String)
