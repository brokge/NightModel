package com.example.nightmodel.app;

import android.app.Application;
import android.content.Context;

import com.example.nightmodel.app.cache.AppConfig;

/**
 * 全局
 *
 * */
public class NightModelApplication extends Application {

    public static Context mContext;
    public static AppConfig appConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        appConfig = new AppConfig(mContext);

    }
}