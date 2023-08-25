package com.msw.springboot.config

import com.msw.aldkli.AldkliManager
import com.msw.aldkli.meta.ApiEntry
import com.msw.aldkli.scanner.ApiScanner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.event.ContextRefreshedEvent

@ComponentScan(value = ["com.msw.aldkli"])
class AldkliConfiguration : ApplicationListener<ContextRefreshedEvent> {

    constructor(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    private var applicationContext: ApplicationContext

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val apiEntryMap = applicationContext.getBeansOfType(ApiEntry::class.java)
        if (apiEntryMap.isEmpty()) return
        val apiScanner = ApiScanner()
        for (entry in apiEntryMap.entries) {
            val apiEntry = entry.value
            val apiGroupMetaDataList = apiScanner.scan(apiEntry.scanPackagePath)
            apiEntry.setApiGroupMetaDataList(apiGroupMetaDataList)
            AldkliManager.addApiEntry(apiEntry)
        }
    }

}