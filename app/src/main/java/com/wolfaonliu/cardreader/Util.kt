package com.wolfaonliu.cardreader

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import java.util.regex.Pattern

object Util {
    //弹窗
    @JvmStatic
    fun aToast(string: String?, activity: Activity?) {
        Toast.makeText(activity, string, Toast.LENGTH_SHORT).show()
    }

    //字节转换成16进制
    @JvmStatic
    fun byteToHex(bArr: ByteArray): String {
        var i = 0
        val strArr =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var str = StringBuilder()
        while (i < bArr.size) {
            val i2: Int = bArr[i].toInt() and 0xFF
            str = StringBuilder(str.toString() + strArr[i2 shr 4 and 0xF] + strArr[i2 and 0xF])
            i++
        }
        return str.toString()
    }
    @JvmStatic
    fun unicodeBlockOfZ(str: String?): Boolean {
        val toCharArray = Pattern.compile("\\s*|\t*|\r*|\n*").matcher(str).replaceAll("")
            .replace("\\p{P}".toRegex(), "").trim { it <= ' ' }
            .toCharArray()
        val length = toCharArray.size.toFloat()
        var f = 0.0f
        for (c in toCharArray) {
            if (!(Character.isLetterOrDigit(c) || unicodeBlock(c))) {
                f += 1.0f
            }
        }
        return (f / length).toDouble() > 0.4
    }

    private fun unicodeBlock(c: Char): Boolean {
        val of = Character.UnicodeBlock.of(c)
        val o = of === Character.UnicodeBlock.GENERAL_PUNCTUATION
        val p = of === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        val q = of === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
        val x = of === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        val y = of === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
        val z = of === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        return x || y || z || o || p || q
    }

    @JvmStatic
    fun isNone2(str: String?): Boolean {
        val x = str == null
        val y = str!!.trim { it <= ' ' } == ""
        return x || y
    }

    //判断有数据
    @JvmStatic
    fun isNotNone3(str: String?): Boolean {
        val x = str == null
        val y = str!!.trim { it <= ' ' } == ""
        val z = str.equals("null", ignoreCase = true)
        return !(x || y || z)
    }

    //获取版本号
    @JvmStatic
    fun getVersion(context: Context): String {
        var ver = ""
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            ver = packageInfo.versionName
            if (ver == "" || ver.isEmpty()) {
                return ""
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ver
    }
}