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

    public static boolean a(String str) {
        char[] toCharArray = Pattern.compile("\\s*|\t*|\r*|\n*").matcher(str).replaceAll("").replaceAll("\\p{P}", "").trim().toCharArray();
        float length = (float) toCharArray.length;
        float f = 0.0f;
        for (char c : toCharArray) {
            if (!(Character.isLetterOrDigit(c) || a(c))) {
                f += 1.0f;
            }
        }
        return ((double) (f / length)) > 0.4d;
    }

    public static boolean a(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.GENERAL_PUNCTUATION || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean d(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean g(String str) {
        return !(str == null || str.trim().equals("") || str.equalsIgnoreCase("null"));
    }

    public static String getVersion(Context context) {
        String ver = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            ver = packageInfo.versionName;
            if (ver == "" || ver.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ver;
    }

}
