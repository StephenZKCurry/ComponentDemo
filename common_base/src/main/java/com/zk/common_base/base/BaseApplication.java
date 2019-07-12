package com.zk.common_base.base;

import android.app.Application;
import android.content.Context;

/**
 * @description: 全局Application
 * @author: zhukai
 * @date: 2018/11/30 14:56
 */
public class BaseApplication extends Application {

    protected static Context mContext;
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mApplication = this;
    }

    public static Context getContext() {
        return mContext;
    }

    public static BaseApplication getApplication() {
        return mApplication;
    }
}
