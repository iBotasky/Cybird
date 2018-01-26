package com.sirius.cybird.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.sirius.cybird.CybirdApplication;

import java.util.Set;

public class SPUtils {
  private static final String NAME = "Cybird";
  private static final Context context = CybirdApplication.Companion.getContext();
  private static SharedPreferences sp = null;
  private static SharedPreferences.Editor editor = null;

  static {
    sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    editor = sp.edit();
  }

  /**
   * 初始化SharedPreferences
   */
  private static SharedPreferences initSP(String spName) {
    SharedPreferences sp;
    if (TextUtils.isEmpty(spName)) {
      sp = PreferenceManager.getDefaultSharedPreferences(context);
    } else {
      sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }
    return sp;
  }

  /**
   * 清除默认的SharedPreferences
   */
  public static void clearPreference() {
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    editor.commit();
  }

  /**
   * 清除指定的SharedPreferences
   */
  public static void clearPreference(String spName) {
    SharedPreferences sp = initSP(spName);
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    editor.commit();
  }

  /**
   * 清除指定的SharedPreferences的指定key
   */
  public static void removeKey(String spName, String key) {
    SharedPreferences sp = initSP(spName);
    SharedPreferences.Editor editor = sp.edit();
    editor.remove(key);
    editor.commit();
  }

  /**
   * 清除默认SharedPreferences的指定key
   */
  public static void removeKey(String key) {
    editor.remove(key);
    editor.commit();
  }

  /**
   * 获取默认SharedPreferences的String数据
   */
  public static String getString(String key, String defaultValue) {
    return sp.getString(key, defaultValue);
  }

  /**
   * 获取指定SharedPreferences的String数据
   */
  public static String getString(String spName, String key, String defaultValue) {
    return initSP(spName).getString(key, defaultValue);
  }

  /**
   * 将String数据存储在默认的SharedPreferences
   */
  public static void setString(String key, String value) {
    editor.putString(key, value).commit();
  }

  /**
   * 将String数据存储在默认的SharedPreferences
   */
  public static void setStringTime(String key, String value) {
    editor.putString(key, value).apply();//apply()提交的数据会覆盖之前的,这个需求正是我们需要的结果
  }

  /**
   * 将String数据存储在指定的SharedPreferences
   */
  public static void setString(String spName, String key, String value) {
    initSP(spName).edit().putString(key, value).commit();
  }

  /**
   * 获取默认SharedPreferences的Boolean值
   */
  public static boolean getBoolean(String key, boolean defaultValue) {
    return sp.getBoolean(key, defaultValue);
  }

  /**
   * 获取指定SharedPreferences的Boolean值
   */
  public static boolean getBoolean(String spName, String key, boolean defaultValue) {
    return initSP(spName).getBoolean(key, defaultValue);
  }

  /**
   * 判断默认的SharedPreferences中是否包含某值
   */
  public static boolean hasContainKey(String key) {
    return sp.contains(key);
  }

  /**
   * 判断指定的SharedPreferences中是否包含某值
   */
  public static boolean hasContainKey(String spName, String key) {
    return initSP(spName).contains(key);
  }

  /**
   * 将Boolean的值保存在默认的SharedPreferences中
   */
  public static void setBoolean(String key, boolean value) {
    editor.putBoolean(key, value).commit();
  }

  /**
   * 将Boolean的值保存在指定的SharedPreferences中
   */
  public static void setBoolean(String spName, String key, boolean value) {
    initSP(spName).edit().putBoolean(key, value).commit();
  }

  /**
   * 将int值保存在默认的SharedPreferences中
   */
  public static void setInt(String key, int value) {
    editor.putInt(key, value).commit();
  }

  /**
   * 将int值保存在指定的SharedPreferences中
   */
  public static void setInt(String spName, String key, int value) {
    initSP(spName).edit().putInt(key, value).commit();
  }

  /**
   * 从默认的SharedPreferences中获取int值
   */
  public static int getInt(String key, int defaultValue) {
    return sp.getInt(key, defaultValue);
  }

  /**
   * 从指定的SharedPreferences中获取int值
   */
  public static int getInt(String spName, String key, int defaultValue) {
    return initSP(spName).getInt(key, defaultValue);
  }

  /**
   * 将float值保存在默认的SharedPreferences中
   */
  public static void setFloat(String key, float value) {
    editor.putFloat(key, value).commit();
  }

  /**
   * 将float值保存在指定的SharedPreferences中
   */
  public static void setFloat(String spName, String key, float value) {
    initSP(spName).edit().putFloat(key, value).commit();
  }

  /**
   * 从默认的SharedPreferences中获取long值
   */
  public static float getFloat(String key, float defaultValue) {
    return sp.getFloat(key, defaultValue);
  }

  /**
   * 从指定的SharedPreferences中获取float值
   */
  public static float getFloat(String spName, String key, float defaultValue) {
    return initSP(spName).getFloat(key, defaultValue);
  }

  /**
   * 将long值保存在默认的SharedPreferences中
   */
  public static void setLong(String key, long value) {
    sp.edit().putLong(key, value).commit();
  }

  /**
   * 将long值保存在指定的SharedPreferences中
   */
  public static void setLong(String spName, String key, long value) {
    initSP(spName).edit().putLong(key, value).commit();
  }

  /**
   * 从默认的SharedPreferences中获取long值
   */
  public static long getLong(String key, long defaultValue) {
    return sp.getLong(key, defaultValue);
  }

  /**
   * 从指定的SharedPreferences中获取long值
   */
  public static long getLong(String spName, String key, long defaultValue) {
    return initSP(spName).getLong(key, defaultValue);
  }

  /**
   * 将StringSet值保存在默认的SharedPreferences中
   */
  public static void setStringSet(String key, Set<String> values) {
    editor.putStringSet(key, values).commit();
  }

  /**
   * 从默认的SharedPreferences中获取StringSet值
   */
  public static Set<String> getStringSet(String key, Set<String> defaultValue) {
    return sp.getStringSet(key, defaultValue);
  }

}
