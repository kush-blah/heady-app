package com.example.kushsingh.heady_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public class SharedPreferenceManager {

    private final SharedPreferences _sharedPreferences;

    public SharedPreferenceManager(Context AppContext, String str) {
        this._sharedPreferences = AppContext.getSharedPreferences(str, 0);
    }

    public SharedPreferences.Editor edit() {
        return this._sharedPreferences.edit();
    }

    public void clear() {
        this._sharedPreferences.edit().clear().apply();
    }

    public void remove(String str) {
        this._sharedPreferences.edit().remove(str).apply();
    }

    public Map<String, ?> getAll() {
        return this._sharedPreferences.getAll();
    }

    public String getString(String str, String str2) {
        return this._sharedPreferences.getString(str, str2);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this._sharedPreferences.getStringSet(str, set);
    }

    public int getInt(String str, int i) {
        return this._sharedPreferences.getInt(str, i);
    }

    public long getLong(String str, long j) {
        return this._sharedPreferences.getLong(str, j);
    }

    public float getFloat(String str, float f) {
        return this._sharedPreferences.getFloat(str, f);
    }

    public boolean getBoolean(String str, boolean z) {
        return this._sharedPreferences.getBoolean(str, z);
    }

    public boolean contains(String str) {
        return this._sharedPreferences.contains(str);
    }

    public void set(String str, String str2) {
        set(str, str2, false);
    }

    public void set(String str, Set<String> set) {
        set(str, set, false);
    }

    public void set(String str, int i) {
        set(str, i, false);
    }

    public void set(String str, long j) {
        set(str, j, false);
    }

    public void set(String str, float f) {
        set(str, f, false);
    }

    public void set(String str, boolean z) {
        set(str, z, false);
    }

    public void set(String str, String str2, boolean z) {
        SharedPreferences.Editor putString = this._sharedPreferences.edit().putString(str, str2);
        if (z) {
            putString.apply();
        } else {
            putString.apply();
        }
    }

    public void set(String str, Set<String> set, boolean z) {
        SharedPreferences.Editor putStringSet = this._sharedPreferences.edit().putStringSet(str, set);
        if (z) {
            putStringSet.apply();
        } else {
            putStringSet.apply();
        }
    }

    public void set(String str, int i, boolean z) {
        SharedPreferences.Editor putInt = this._sharedPreferences.edit().putInt(str, i);
        if (z) {
            putInt.apply();
        } else {
            putInt.apply();
        }
    }

    public void set(String str, long j, boolean z) {
        SharedPreferences.Editor putLong = this._sharedPreferences.edit().putLong(str, j);
        if (z) {
            putLong.apply();
        } else {
            putLong.apply();
        }
    }

    public void set(String str, float f, boolean z) {
        SharedPreferences.Editor putFloat = this._sharedPreferences.edit().putFloat(str, f);
        if (z) {
            putFloat.apply();
        } else {
            putFloat.apply();
        }
    }

    public void set(String str, boolean z, boolean z2) {
        SharedPreferences.Editor putBoolean = this._sharedPreferences.edit().putBoolean(str, z);
        if (z2) {
            putBoolean.apply();
        } else {
            putBoolean.apply();
        }
    }
}
