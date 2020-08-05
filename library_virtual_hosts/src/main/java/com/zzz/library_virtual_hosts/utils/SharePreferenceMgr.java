package com.zzz.library_virtual_hosts.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * share 文件帮助类
 */
public class SharePreferenceMgr {

    private String SHARE_FILE_NAME = "v_hosts";

    private static volatile SharePreferenceMgr instance = null;
    private static Context mContext;

    public static SharePreferenceMgr getInstance(Context context){
        mContext = context;
        if (instance == null){
            synchronized (SharePreferenceMgr.class){
                if (instance == null){
                    instance = new SharePreferenceMgr();
                }
            }
        }
        return instance;
    }

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharePreferenceMgr(){
        sp = mContext.getSharedPreferences(SHARE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    /**
     * 保存数据
     * @param key
     * @param object
     */
    public void put(String key, Object object){
        if (editor == null){
            new SharePreferenceMgr();
        }
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        editor.commit();
    }


    /**
     * 获取数据
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String key, Object defaultValue){
        if (sp == null){
            new SharePreferenceMgr();
        }

        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }

        return null;
    }


    /**
     * 删除
     * @param key
     */
    public void remove(String key){
        if (editor == null){
            new SharePreferenceMgr();
        }

        editor.remove(key);
        editor.commit();
    }


    /**
     * 判断是否包含key
     * @param key
     * @return
     */
    public boolean contains(String key){
        if (sp == null){
            new SharePreferenceMgr();
        }

        return sp.contains(key);
    }


    /**
     * 清空数据
     */
    public void clear(){
        if (editor == null){
            new SharePreferenceMgr();
        }

        editor.clear();
        editor.commit();
    }

}
