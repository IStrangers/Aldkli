package com.msw.aldkli

import com.msw.aldkli.meta.ApiEntry
import com.msw.aldkli.scanner.ApiScanner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent

@Configuration
@ComponentScan(value = ["com.msw.aldkli"])
class AldkliAutoConfiguration(private var applicationContext: ApplicationContext) : ApplicationListener<ContextRefreshedEvent> {

    @Bean
    fun aldkliContext(): AldkliContext = AldkliContext()

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val apiEntryMap = applicationContext.getBeansOfType(ApiEntry::class.java)
        if (apiEntryMap.isEmpty()) return
        val apiScanner = ApiScanner()
        val aldkliContext = aldkliContext()
        for (entry in apiEntryMap.entries) {
            val apiEntry = entry.value
            val apiGroupMetaDataList = apiScanner.scan(apiEntry.scanPackagePath)
            apiEntry.setApiGroupMetaDataList(apiGroupMetaDataList)
            aldkliContext.addApiEntry(apiEntry)
        }
    }

}