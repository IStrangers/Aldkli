package com.msw.aldkli.util;

import java.io.File
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

class ClassUtil {

    companion object {

        fun scanClass(packagePath: String, classFilter: ((KClass<out Any>) -> Boolean)?): ArrayList<KClass<out Any>> {
            return findClassList(packagePath,classFilter)
        }

        fun findClassList(packagePath: String,classFilter: ((KClass<out Any>) -> Boolean)?): ArrayList<KClass<out Any>> {
            var name = packagePath
            if (!name.startsWith("/")) {
                name = "/$name"
            }
            name = name.replace('.', '/')

            val url = ClassUtil::class.java.getResource(name)
            val directory = if(url == null) null else File(url.file)

            var classList = ArrayList<KClass<out Any>>()
            if (directory?.exists() == true) {
                directory.walk()
                    .filter { it.isFile && !it.name.contains('$') && it.name.endsWith(".class") }
                    .forEach {
                        val fullyQualifiedClassName = packagePath + it.canonicalPath.removePrefix(directory.canonicalPath).dropLast(6).replace('/', '.').replace('\\', '.')
                        val kClass = Class.forName(fullyQualifiedClassName).kotlin
                        if(classFilter != null && !classFilter(kClass)) return@forEach
                        classList.add(kClass)
                    }
            }
            return classList
        }

        fun getGenericTypeName(genericType: Type): String {
            val typeName = StringBuilder()
            if (genericType is ParameterizedType) {
                val rawType = genericType.rawType
                typeName.append(if(rawType is Class<*>) rawType.simpleName else rawType.typeName)
                val actualTypeArguments = genericType.actualTypeArguments
                if (actualTypeArguments.isNotEmpty()) {
                    typeName.append("<")
                    typeName.append(actualTypeArguments.joinToString(",") { getGenericTypeName(it) })
                    typeName.append(">")
                }
            } else {
                typeName.append(if(genericType is Class<*>) genericType.simpleName else genericType.typeName)
            }
            return typeName.toString();
        }

    }

}
