package com.shuwen.network.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.text.TextUtils
import com.shuwen.network.kt.Constants

/**
 * 工具类
 * @author lilei
 * @since 2019-10-24
 * @version 1.0
 */

/**
 * 获取指定上下文对应的对外版本号
 * @param context 上下文
 */
private fun getVersionName(context: Context): String {
    // 获取packagemanager的实例
    val packageManager = context.packageManager
    // getPackageName()是你当前类的包名，0代表是获取版本信息
    var packInfo: PackageInfo? = null
    try {
        packInfo = packageManager.getPackageInfo(context.packageName, 0)
        return packInfo!!.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return "ERROR"
}

/**
 * 获取通用请求头
 * @param context 上下文
 * @param token 用户身份令牌
 */
fun getCommonRequestHeaders(context: Context?, uuid: String, token: String?): Map<String, String> {
    var headers: HashMap<String, String> = HashMap()
    if (context != null) {
        // 添加客户端类型
        headers[Constants.CLIENT_TYPE] = "1"
        headers[Constants.USER_ID] = uuid
        // 添加应用版本号
        headers[Constants.APP_VERSION_NAME] =
            getVersionName(context)
        // 有用户身份令牌，则添加
        if (!TextUtils.isEmpty(token)) {
            headers[Constants.TOKEN] = token!!
        }
    }
    return headers
}