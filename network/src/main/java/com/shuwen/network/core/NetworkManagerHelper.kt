package com.shuwen.network.core

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 网络请求管理工具助手
 */
open class NetworkManagerHelper<T> {

    /**
     * 通过反射获取泛型实例
     */
   private fun getType(): Class<T>? {
        // 获取该类的所有父类，包含泛型，getSuperclass不包含泛型
        val type: Type = javaClass.genericSuperclass
        // 获取所有泛型的类型
        val types: Array<Type> = (type as ParameterizedType).actualTypeArguments
        var clazz: Class<T>? = null
        if (types.isEmpty()) {
            IllegalArgumentException("please set request api interface!")
        } else {
            // 使用第一个，就是我们模块中定义的api接口
            clazz = types[0] as Class<T>
        }
        return clazz
    }

    /**
     * 请求
     */
    public fun request(): T {
        return NetWorkManager.getInstance().getRetrofit()!!.create(getType())
    }

    /**
     * 带参请求
     * @param params 参数
     */
    public fun request(params: Map<String, String>): T {
        return NetWorkManager.getInstance().getRetrofit()!!.create(getType())
    }


}