package com.wolfaonliu.cardreader;

//TODO 工具类更名+整理
public class e {
    //byte 1字节=8 bit
    public static String b(byte[] bArr) {
        //新建一个可变长度的字符串序列
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
        return stringBuilder.toString();
    }


    public static String d(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte aBArr : bArr) {
            stringBuilder.append((byte) ((aBArr & 240) >>> 4));
            stringBuilder.append((byte) (aBArr & 15));
        }
        return stringBuilder.toString().substring(0, 1).equalsIgnoreCase("0") ? stringBuilder.toString().substring(1) : stringBuilder.toString();
    }

    //被外部引用
    public static String e1(byte[] bArr, int i) {
        byte[] bArr2 = new byte[7];
        g.a(bArr, i, bArr2, 0, 7);
        return d(bArr2);
    }

}
