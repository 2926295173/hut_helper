package com.wolfaonliu.cardreader;


//TODO 工具类更名
//重载很多
public class g {
    //define byte array
    private static final byte[] a = new byte[]{
            (byte) 48, (byte) 49, (byte) 50, (byte) 51,
            (byte) 52, (byte) 53, (byte) 54, (byte) 55,
            (byte) 56, (byte) 57, (byte) 65, (byte) 66,
            (byte) 67, (byte) 68, (byte) 69, (byte) 70};

    //return
    private static byte a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String a(byte[] bArr, int i) {
        //计数
        int i2 = 0;
        //新建数组，长度为
        byte[] bArr2 = new byte[(i * 2)];
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            byte b = bArr[i2];
            if (i3 >= i) {
                break;
            }
            i3++;
            //取8位，返回整数
            int i5 = b & 255;
            //取高4位
            bArr2[i4] = a[i5 >>> 4];
            int i6 = i4 + 1;
            i4 = i6 + 1;
            //取低四位
            bArr2[i6] = a[i5 & 15];
            i2++;
        }
        return new String(bArr2);
    }

    public static String a(byte[] bArr, int i, int i2, String str) {
        try {
            byte[] bArr2 = new byte[i2];
            a(bArr, i, bArr2, 0, i2);
            return new String(bArr2, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //来自e的引用
    public static void a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i2 + i3 > bArr2.length) {
            throw new RuntimeException("目标字节数组所分配的长度不够");
        } else if (i + i3 > bArr.length) {
            throw new RuntimeException("源字节数组的长度与要求复制的长度不符");
        } else {
            System.arraycopy(bArr, i, bArr2, i2, i3);
        }
    }

    public static byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        char[] toCharArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (a(toCharArray[i2 + 1]) | (a(toCharArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        byte[] obj = new byte[(bArr.length - 2)];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        return obj;
    }

    public static boolean c(byte[] bArr) {
        //new 2 byte array
        byte[] obj = new byte[2];
        //src src.pos dest dest.pos len
        //从第4位开始复制，复制第四第五位到obj
        System.arraycopy(bArr, bArr.length - 2, obj, 0, 2);
        //传2个byte，判断是否为9000
        return a(obj, 2).equals("9000");
    }
}
