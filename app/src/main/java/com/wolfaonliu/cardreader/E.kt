package com.wolfaonliu.cardreader

//TODO 工具类更名
object E {
    //byte 1字节=8 bit
    @JvmStatic
    fun b(bArr: ByteArray): String {
        //新建一个可变长度的字符串序列，长度为原来长度
        val stringBuilder = StringBuilder(bArr.size)
        //迭代原始数组
        for (b in bArr) {

            //取八位,转化成16进制的字符
            val toHexString = Integer.toHexString(b.toInt() and 0xFF)
            //判断字符长度是否小于2，是的话就填0（允许 0-F）
            if (toHexString.length < 2) {
                stringBuilder.append(0)
            }
            //长度等于2就添加上大写的16进制
            stringBuilder.append(toHexString.uppercase())
        }
        //返回字符串数组
        return stringBuilder.toString()
    }

    //被外部引用
    @JvmStatic
    fun e1(bArr: ByteArray?, i: Int): String {
        //新建长度为7的字节数组
        val bArr2 = ByteArray(7)
        if (bArr != null) {
            g.a(bArr, i, bArr2, 0, 7)
        }

        //新建可变长字符串，长度为原来2倍
        val stringBuilder = StringBuilder(bArr2.size * 2)
        //迭代原来中字符
        for (aBArr in bArr2) {
            //240=1111 0000,取高4位单独存起来byte
            //maosiyouwenti
            stringBuilder.append((aBArr.toInt() and 0xF0 ushr 4).toByte())
            //15=0000 1111 ，取低四位byte
            stringBuilder.append((aBArr.toInt() and 0xF).toByte())
        }
        //用新字符串中第一个字符与0进行比较，是则返回0后面的，不是则全返回
        return if (stringBuilder.substring(0, 1)
                .equals("0", ignoreCase = true)
        ) stringBuilder.substring(1) else stringBuilder.toString()
    }
}