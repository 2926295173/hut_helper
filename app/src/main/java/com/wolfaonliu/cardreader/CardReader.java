package com.wolfaonliu.cardreader;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;

import java.io.IOException;

public class CardReader {


    public static CardInfo readCard(Activity activity, Intent intent) {
        //TODO 拆分成多个方法
        CardInfo card = new CardInfo(activity);

        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent == null)
            return null;
        card.setHardwareId(Util.byteToHex(tagFromIntent.getId()));


        String action = intent.getAction();
        if (!"android.nfc.action.TAG_DISCOVERED".equals(action) && !"android.nfc.action.TECH_DISCOVERED".equals(action) && !"android.nfc.action.NDEF_DISCOVERED".equals(action)) {
            return null;
        }

        IsoDep isoDep = IsoDep.get((Tag) intent.getParcelableExtra("android.nfc.extra.TAG"));
        if (isoDep == null) {
//            extraID();
//            unicodeBlockOfZ(this.NfcMainfare);
            return null;
        }


        try {

            isoDep.connect();
//            unicodeBlockOfZ(this.NfcMainfare);
//            extraID();
            isoDep.transceive(g.a(fInts.a));
            byte[] transceive = isoDep.transceive(g.a(fInts.c));
            if (transceive != null && g.c(transceive)) {
                transceive = isoDep.transceive(g.a(fInts.d));
                if (transceive == null || !g.c(transceive)) {
                    Util.aToast(activity.getString(R.string.read_failed), activity);
                } else {
                    byte[] a;


                    transceive = isoDep.transceive(g.a(fInts.j));
                    //名字
                    if (transceive != null && g.c(transceive)) {
                        a = g.a(transceive);
                        g.a(a, 0, transceive, 0, transceive.length - 2);
                        action = g.a(a, 0, a.length, "GB18030").trim();
//                        Log.isNone2("名字", action);
                        if (Util.isNotNone3(action)) {
                            card.setStudentName(action);
//                            Log.isNone2("名字", action);
//                            aToast(action);
//                            Log.isNone2("名字","r1");
                        }
                    }

                    transceive = isoDep.transceive(g.a(fInts.h));
                    //卡号
                    if (transceive != null && g.c(transceive)) {
                        a = g.a(transceive);
                        g.a(a, 0, transceive, 0, transceive.length - 2);
                        action = g.a(a, 0, a.length, "GB18030").trim();
//                        Log.isNone2("卡号", action);
                        card.setStudentId(action);
                    }

                    transceive = isoDep.transceive(g.a(fInts.personId));
                    //身份证
                    if (transceive != null && g.c(transceive)) {
                        a = g.a(transceive);
                        g.a(a, 0, transceive, 0, transceive.length - 2);
                        action = g.a(a, 0, a.length, "GB18030").trim();
//                        Log.isNone2("卡号", action);
                        card.setPersonId(action);
                    }


                    transceive = isoDep.transceive(g.a(fInts.g));
                    //学院
                    if (transceive != null && g.c(transceive)) {
                        a = g.a(transceive);
                        g.a(a, 0, transceive, 0, transceive.length - 2);
                        action = g.a(a, 0, a.length, "GB18030").trim();
//                        Log.isNone2("***", action);
                        if (Util.isNone2(action) || Util.unicodeBlockOfZ(action) || action.contains("000000")) {
//                                    bVar.isNone2(action);
//                            Log.isNone2("学院******", action);
                        } else {
//                          bVar.c(action);
//                            Log.isNone2("学院***", action);
                            card.setStudentDept(action);
                        }
                    }

                    int intValue;
                    transceive = isoDep.transceive(g.a(fInts.e));
                    //余额
                    if (transceive != null && g.c(transceive)) {
                        transceive = isoDep.transceive(g.a(fInts.n));
                        if (transceive != null && g.c(transceive)) {
                            transceive = g.a(transceive);
                            intValue = Integer.valueOf(g.a(transceive, transceive.length), 16);
                            action = (intValue / 100) + "." + (intValue % 100);

//                                    bVar.e(action);
//                            Log.isNone2("余额", action);
                            card.setCardBalance(action);
                        }
                    }


                    ReadTrade(isoDep, card);

//测试部分，用于遍历卡内储存地址
//
//                    for(int j=0;j<fInts.tt.length;j++)
////                        transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(fInts.tt[j]));
//                        for(int ii=0x961600;ii<0x961700;ii=ii+0x1){
//                            String sss;
//                        if(ii<=0xF)
//                            sss="00B0"+"00000"+Integer.toHexString(ii).toUpperCase();
//                        else if(ii<0xFF)
//                            sss="00B0"+"0000"+Integer.toHexString(ii).toUpperCase();
//                        else if(ii<0xFFF)
//                            sss="00B0"+"000"+Integer.toHexString(ii).toUpperCase();
//                        else if(ii<0xFFFF)
//                            sss="00B0"+"00"+Integer.toHexString(ii).toUpperCase();
//                        else if(ii<0xFFFFF)
//                            sss="00B0"+"0"+Integer.toHexString(ii).toUpperCase();
//                        else
//                            sss="00B0"+""+Integer.toHexString(ii).toUpperCase();
//
//
//                            Log.isNone2("===", "============="+j+","+sss+"=================");
//
//                            transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(fInts.tt[j]));
//                    //测试
//                    if (transceive != null && isNotNone3.c(transceive)) {
//                        transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(sss));
//                        if (transceive != null && isNotNone3.c(transceive)) {
////                            Log.isNone2("测试", byteToHex(transceive));
//                            transceive = isNotNone3.unicodeBlockOfZ(transceive);
////                            System.out.println(transceive);
////                            for(byte e : transceive) {
////                                System.out.print(e + " ");
////                            }
//                            String str2=new String(transceive);
////                            System.out.println("\n打印2："+str2);
////                            Log.isNone2("测试", byteToHex(transceive));
////                            Log.isNone2("测试", transceive.toString().trim());
//                            Log.isNone2("测试", new String(transceive,"GB18030").trim());
////                            Log.isNone2("测试", new String(transceive,"UTF-8").trim());
////                            Log.isNone2("测试", new String(transceive,"ISO-8859-1").trim());
//
//                            try {
//                                intValue = Integer.valueOf(isNotNone3.unicodeBlockOfZ(transceive, transceive.length), 16).intValue();
//                                action = (intValue / 100) + "." + (intValue % 100);
////                                    bVar.e(action);
//                                Log.isNone2("测试", action);
//                            }catch (NumberFormatException e){
//                                Log.isNone2("测试", "boom");
//                            }
//
////                            card.setCardBalance(action);
//                        }else{
////                            Log.isNone2("测试", "无2");
//                        }
////                        unicodeBlockOfZ(isoDep);
//                    }else{
////                        Log.isNone2("测试", "无1");
//                    }
////                                Log.isNone2("===", "======================================");
//
//                    }
//
//for(int j=0;j<fInts.tt.length;j++) {
//    transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(fInts.tt[j]));
//    if (transceive != null && isNotNone3.c(transceive)) {
//        unicodeBlockOfZ = isNotNone3.ReadTrade(transceive);
//        Log.isNone2("测试0,"+j, String.valueOf(unicodeBlockOfZ));
//        Log.isNone2("测试0.5,"+j, isNotNone3.unicodeBlockOfZ(transceive, transceive.length));
//        isNotNone3.unicodeBlockOfZ(ReadTrade, 0, transceive, 0, transceive.length - 2);
//        Log.isNone2("测试1,"+j, String.valueOf(unicodeBlockOfZ));
//        action = isNotNone3.unicodeBlockOfZ(ReadTrade, 0, ReadTrade.length, "UTF-8").trim();
////                        Log.isNone2("***", action);
//        Log.isNone2("测试2,"+j, action);
//        Log.isNone2("eiiiiii", "======================================");
//        if (Util.isNone2(action) || unicodeBlockOfZ(action) || action.contains("000000")) {
////                                    bVar.isNone2(action);
////                            Log.isNone2("学院******", action);
//        } else {
////                          bVar.c(action);
////                            Log.isNone2("学院***", action);
////                            card.setStudentDept(action);
//        }
//    } else {
//        Log.isNone2("测试", "无,"+j);
//        Log.isNone2("eiiiiii", "======================================");
//    }
//
//}
//
//
//                    //未知3，同交易记录
//                    transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(fInts.f));
//                    if (transceive != null && isNotNone3.c(transceive)) {
//                        transceive = isoDep.transceive(isNotNone3.unicodeBlockOfZ(fInts.n));
//                        if (transceive != null && isNotNone3.c(transceive)) {
//                            transceive = isNotNone3.unicodeBlockOfZ(transceive);
//                            intValue = Integer.valueOf(isNotNone3.unicodeBlockOfZ(transceive, transceive.length), 16);
//                            action = (intValue / 100) + "." + (intValue % 100);
////                                    bVar.f(action);
//                            Log.isNone2("未知3***", action);
//
//                        }
//                        ReadTrade(isoDep);
//                    }
////                    Log.isNone2("***", String.valueOf(Float.parseFloat(this.w) + Float.parseFloat(this.x)) + "元");


                }
            }
            try {
                isoDep.close();
            } catch (IOException e) {
                Log.d("ERR", "close fail");
                e.printStackTrace();
            }
        } catch (IOException e2) {
            String str = "****";
            Log.d("ERR", "ERROR");
            action = e2.getLocalizedMessage() == null ? "" : e2.getLocalizedMessage();
            Log.e(str, action);
            try {
                isoDep.close();
            } catch (IOException e22) {
                Log.d("ERR", "close fail");
                e22.printStackTrace();
            }

        }
        return card;
    }


    public static void ReadTrade(IsoDep isoDep, CardInfo card) {
        for (int i = 0; i < 10; i++) {
            byte[] a = g.a(fInts.t);
            a[2] = (byte) (i + 1);
            try {
                a = isoDep.transceive(a);
//                DebugUtil.unicodeBlockOfZ("读钱包交易记录：", e.b(readCard));
//                Log.isNone2("读钱包交易记录：", e.b(unicodeBlockOfZ));
                if (a != null && g.c(a) && Util.isNotNone3(E.b(g.a(a)).replaceAll("0", ""))) {
                    TradingRecordInfo tradingRecordInfo = new TradingRecordInfo();
                    byte[] bArr = new byte[6];
                    byte[] bArr2 = new byte[1];
                    byte[] bArr3 = new byte[4];
                    g.a(a, 5, bArr3, 0, bArr3.length);
                    g.a(a, 9, bArr2, 0, bArr2.length);
                    g.a(a, 10, bArr, 0, bArr.length);
                    tradingRecordInfo.setTradingDateTime(E.e1(a, 16));
                    tradingRecordInfo.setTradingType(Integer.valueOf(E.b(bArr2), 16));
                    tradingRecordInfo.setTradingMoney(Long.valueOf(E.b(bArr3), 16));
//                    if (this.CJ != null) {
//                    Log.isNone2("读钱包交易记录：", String.valueOf(Long.valueOf(e.b(bArr3), 16).longValue()));
//                    Log.isNone2("读钱包交易记录：", String.valueOf(Integer.valueOf(e.b(bArr2), 16).intValue()));
//                    Log.isNone2("读钱包交易记录：", e.e1(unicodeBlockOfZ, 16));
                    card.addDeal(tradingRecordInfo);
                    //TODO 读取交易信息至ListView中
                    //this.CJ.NfcTradeInfo(tradingRecordInfo);
//                    }
                }
            } catch (IOException e) {
//                                Log.isNone2("读钱包交易记录：", "boom");
                e.printStackTrace();
            }
        }
    }

}
