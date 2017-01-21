package com.org.zl.welcomedemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;

/**
 * @Description: 监测网络(用一句话描述该文件做什么)
 * 作者：朱亮 on 2017/1/20 19:31
 * 邮箱：171422696@qq.com
 * ${监测网络}(这里用一句话描述这个方法的作用)
 */
public class NetUtil {

    /** 验证是否存在可用网络 */
    public static boolean CheckNetworkState(Context mContext) {
        int state = currentNetwork(mContext);
        return state < 2 ? true : false;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
    /** 验证网络状态 0 存在wifi网络， 1 存在2/3G网络，2无网络连接 */
    public static int currentNetwork(Context mContext) {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo.State mobile = null, wifi = null;
        try {
            mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState();
        } catch (Exception e) {

        }
        try {
            wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        // 判断当前连接的网络 返回相对应的状态
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            return 0;
        } else if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            return 1;
        } else {
            return 2;
        }
    }
}