package com.example.luckyluke.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class CustomPreferences implements SharedPreferences {

    private SharedPreferences mSharedPreferences;

    public CustomPreferences(Context mContext) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public ArrayList<LoginInfo> getListLogin(String key, Class<LoginInfo> mClass) {
        Gson mGson = new Gson();
        ArrayList<String> mStrings = new ArrayList<>
                (Arrays.asList(TextUtils.split(mSharedPreferences.getString(key, ""),
                        "‚‗‚")));
        ArrayList<LoginInfo> mLoginInfos = new ArrayList<>();

        for (String objString: mStrings) {
            LoginInfo mInfo = mGson.fromJson(objString, mClass);
            mLoginInfos.add(mInfo);
        }
        return mLoginInfos;
    }

    public void putListLogin(String key, ArrayList<LoginInfo> mList) {
        Gson mGson = new Gson();
        ArrayList<String> mStrings = new ArrayList<>();
        for (LoginInfo loginInfo: mList) {
            mStrings.add(mGson.toJson(loginInfo));
        }

        String[] myStrings = mStrings.toArray(new String[mStrings.size()]);
        mSharedPreferences.edit().putString(key, TextUtils.join("‚‗‚", myStrings)).apply();
    }

    @Override
    public Map<String, ?> getAll() {
        return null;
    }

    @Nullable
    @Override
    public String getString(String s, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String s, @Nullable Set<String> set) {
        return null;
    }

    @Override
    public int getInt(String s, int i) {
        return 0;
    }

    @Override
    public long getLong(String s, long l) {
        return 0;
    }

    @Override
    public float getFloat(String s, float v) {
        return 0;
    }

    @Override
    public boolean getBoolean(String s, boolean b) {
        return false;
    }

    @Override
    public boolean contains(String s) {
        return false;
    }

    @Override
    public Editor edit() {
        return null;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {

    }
}
