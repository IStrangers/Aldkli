import com.fasterxml.jackson.databind.ObjectMapper
import com.msw.aldkli.scanner.ApiScanner

fun main(args: Array<String>) {
    val scanPackage = "com.aix.controller"
    val apiGroupMetaDataList = ApiScanner().scan(scanPackage)
    println(ObjectMapper().writeValueAsString(apiGroupMetaDataList))
}