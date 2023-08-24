import com.alibaba.fastjson2.JSON
import com.msw.aldkli.scanner.ApiScanner

fun main(args: Array<String>) {
    val scanPackage = "com.aix.controller"
    val apiGroupMetaDataList = ApiScanner().scan(scanPackage)
    println(JSON.toJSONString(apiGroupMetaDataList))
}