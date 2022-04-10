package com.wolfaonliu.cardreader

//TODO 工具类更名
//重载很多
object g {
    //define byte array
    private val a = byteArrayOf(
        48.toByte(), 49.toByte(), 50.toByte(), 51.toByte(),
        52.toByte(), 53.toByte(), 54.toByte(), 55.toByte(),
        56.toByte(), 57.toByte(), 65.toByte(), 66.toByte(),
        67.toByte(), 68.toByte(), 69.toByte(), 70.toByte()
    )

    //return
    private fun a(c: Char): Byte {
        return "0123456789ABCDEF".indexOf(c).toByte()
    }

    fun a(bArr: ByteArray, i: Int): String {
        //计数
        var i2 = 0
        //新建数组，长度为
        val bArr2 = ByteArray(i * 2)
        val length = bArr.size
        var i3 = 0
        var i4 = 0
        while (i2 < length) {
            val b = bArr[i2]
            if (i3 >= i) {
                break
            }
            i3++
            //取8位，返回整数
            val i5: Int = b.toInt() and 255
            //取高4位
            bArr2[i4] = a[i5 ushr 4]
            val i6 = i4 + 1
            i4 = i6 + 1
            //取低四位
            bArr2[i6] = a[i5 and 15]
            i2++
        }
        return String(bArr2)
    }

    fun a(bArr: ByteArray, i: Int, i2: Int, str: String?): String? {
        return try {
            val bArr2 = ByteArray(i2)
            a(bArr, i, bArr2, 0, i2)
            String(bArr2, str)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //来自e的引用
    fun a(bArr: ByteArray, i: Int, bArr2: ByteArray, i2: Int, i3: Int) {
        if (i2 + i3 > bArr2.size) {
            throw RuntimeException("目标字节数组所分配的长度不够")
        } else if (i + i3 > bArr.size) {
            throw RuntimeException("源字节数组的长度与要求复制的长度不符")
        } else {
            System.arraycopy(bArr, i, bArr2, i2, i3)
        }
    }

    fun a(str: String): ByteArray {
        val length = str.length / 2
        val bArr = ByteArray(length)
        val toCharArray = str.toCharArray()
        for (i in 0 until length) {
            val i2 = i * 2
            //example 11110101===>0000 0000 0000 0000 0000 0000 0000 0000 .... 1111 0101
            val x = a(toCharArray[i2 + 1]).toInt()
            //example
            val y = (a(toCharArray[i2]).toInt() shl 4)
            //x or y可能超出byte范围，所以进行其他位清零
            val z = (x or y) and 255
            bArr[i] = z.toByte()
        }
        return bArr
    }

    @JvmStatic
    fun a(bArr: ByteArray): ByteArray {
        val obj = ByteArray(bArr.size - 2)
        System.arraycopy(bArr, 0, obj, 0, obj.size)
        return obj
    }

    @JvmStatic
    fun c(bArr: ByteArray): Boolean {
        //new 2 byte array
        val obj = ByteArray(2)
        //src src.pos dest dest.pos len
        //从第4位开始复制，复制第四第五位到obj
        System.arraycopy(bArr, bArr.size - 2, obj, 0, 2)
        //传2个byte，判断是否为9000
        return a(obj, 2) == "9000"
    }
}