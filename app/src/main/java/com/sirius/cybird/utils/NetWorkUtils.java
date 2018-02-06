package com.sirius.cybird.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络相关的工具类
 *
 * @author: 黄一凡
 * @date: 2017-03-03
 */
public class NetWorkUtils {
  /** 没有网络 */
  public static final int NETWORKTYPE_INVALID = 0;
  /** wap网络 */
  public static final int NETWORKTYPE_WAP = 1;
  /** 2G网络 */
  public static final int NETWORKTYPE_2G = 2;
  /** 3G和3G以上网络，或统称为快速网络 */
  public static final int NETWORKTYPE_3G = 3;
  /** wifi网络 */
  public static final int NETWORKTYPE_WIFI = 4;

  /**
   * 判断当前网络是否可用
   */
  public static boolean isConnected(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE);
    if (null != connectivityManager) {
      NetworkInfo info = connectivityManager.getActiveNetworkInfo();
      if (null != info && info.isConnected()) {
        if (info.getState() == NetworkInfo.State.CONNECTED) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断当前是否为wifi网络环境
   */
  public static boolean isWifi(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
      return true;
    }
    return false;
  }

  public static String GetNetworkType(Context context) {
    String strNetworkType = "";
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

    if (networkInfo != null && networkInfo.isConnected()) {
      if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
        strNetworkType = "WIFI";
      } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
        String _strSubTypeName = networkInfo.getSubtypeName();
        // TD-SCDMA   networkType is 17
        int networkType = networkInfo.getSubtype();
        switch (networkType) {
          case TelephonyManager.NETWORK_TYPE_GPRS:
          case TelephonyManager.NETWORK_TYPE_EDGE:
          case TelephonyManager.NETWORK_TYPE_CDMA:
          case TelephonyManager.NETWORK_TYPE_1xRTT:
          case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
            strNetworkType = "2G";
            break;
          case TelephonyManager.NETWORK_TYPE_UMTS:
          case TelephonyManager.NETWORK_TYPE_EVDO_0:
          case TelephonyManager.NETWORK_TYPE_EVDO_A:
          case TelephonyManager.NETWORK_TYPE_HSDPA:
          case TelephonyManager.NETWORK_TYPE_HSUPA:
          case TelephonyManager.NETWORK_TYPE_HSPA:
          case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
          case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
          case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
            strNetworkType = "3G";
            break;
          case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
            strNetworkType = "4G";
            break;
          default:
            // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
            if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase(
                "WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
              strNetworkType = "3G";
            } else {
              strNetworkType = _strSubTypeName;
            }

            break;
        }

      }
    }
    return strNetworkType;
  }

  /**
   * 获取ip地址
   */
  public static String getHostIP() {

    String hostIp = null;
    try {
      Enumeration nis = NetworkInterface.getNetworkInterfaces();
      InetAddress ia = null;
      while (nis.hasMoreElements()) {
        NetworkInterface ni = (NetworkInterface) nis.nextElement();
        Enumeration<InetAddress> ias = ni.getInetAddresses();
        while (ias.hasMoreElements()) {
          ia = ias.nextElement();
          if (ia instanceof Inet6Address) {
            continue;// skip ipv6
          }
          String ip = ia.getHostAddress();
          if (!"127.0.0.1".equals(ip)) {
            hostIp = ia.getHostAddress();
            break;
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return hostIp;

  }
}
