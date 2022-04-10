package com.wolfaonliu.cardreader;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.regex.Pattern;


public class Util {
    //弹窗
    public static void aToast(String string, Activity activity) {
        Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
    }

    //字节转换成16进制
    public static String byteToHex(byte[] bArr) {
        int i = 0;
        String[] strArr = new String[]
                {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuilder str = new StringBuilder();
        while (i < bArr.length) {
            int i2 = bArr[i] & 0xFF;
            str = new StringBuilder((str + strArr[(i2 >> 4) & 0xF]) + strArr[i2 & 0xF]);
            i++;
        }
        return str.toString();
    }

    public static boolean unicodeBlockOfZ(String str) {
        char[] toCharArray = Pattern.compile("\\s*|\t*|\r*|\n*").matcher(str).replaceAll("").replaceAll("\\p{P}", "").trim().toCharArray();
        float length = (float) toCharArray.length;
        float f = 0.0f;
        for (char c : toCharArray) {
            if (!(Character.isLetterOrDigit(c) || unicodeBlockOfZ(c))) {
                f += 1.0f;
            }
        }
        return ((double) (f / length)) > 0.4d;
    }

    public static boolean unicodeBlockOfZ(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        boolean o = (of == Character.UnicodeBlock.GENERAL_PUNCTUATION);
        boolean p = (of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
        boolean q = (of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
        boolean x = (of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        boolean y = (of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
        boolean z = (of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
        return x || y || z || o || p || q;
    }

    public static boolean isNone2(String str) {
        boolean x = (str == null);
        boolean y = (str.trim().equals(""));
        return x || y;
    }

    //判断有数据
    public static boolean isNotNone3(String str) {
        boolean x = (str == null);
        boolean y = (str.trim().equals(""));
        boolean z = (str.equalsIgnoreCase("null"));
        return !(x || y || z);

    }

    //获取版本号
    public static String getVersion(Context context) {
        String ver = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            ver = packageInfo.versionName;
            if (ver.equals("") || ver.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ver;
    }

}
