package com.wolfaonliu.cardreader;

//TODO 工具类更名+整理
public class e {
    //byte 1字节=8 bit
    public static String b(byte[] bArr) {
        //新建一个可变长度的字符串序列，长度为原来长度
        StringBuilder stringBuilder = new StringBuilder(bArr.length);
        //迭代原始数组
        for (byte b : bArr) {
            byte c = (byte) 255;
            //取八位,转化成16进制的字符
            String toHexString = Integer.toHexString(b & c);
            //判断字符长度是否小于2，是的话就填0（允许 0-F）
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            //长度等于2就添加上大写的16进制
            stringBuilder.append(toHexString.toUpperCase());

        }
        //返回字符串数组
        return stringBuilder.toString();
    }


    public static String d(byte[] bArr) {
        //新建可变长字符串，长度为原来2倍
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        //迭代原来中字符
        for (byte aBArr : bArr) {
            //240=1111 0000,取高4位单独存起来
            stringBuilder.append((byte) ((aBArr & (byte) 240) >>> 4));
            //15=0000 1111 ，取低四位
            stringBuilder.append((byte) (aBArr & (byte) 15));
        }
        return stringBuilder.substring(0, 1).equalsIgnoreCase("0") ?
                stringBuilder.substring(1) : stringBuilder.toString();
    }

    //被外部引用
    public static String e1(byte[] bArr, int i) {
        byte[] bArr2 = new byte[7];
        g.a(bArr, i, bArr2, 0, 7);
        return d(bArr2);
    }

}
