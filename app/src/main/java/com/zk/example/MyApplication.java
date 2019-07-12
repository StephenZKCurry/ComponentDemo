package com.zk.example;

import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.common_base.BuildConfig;
import com.zk.common_base.base.BaseApplication;

/**
 * @description: App壳工程全局Application
 * @author: zhukai
 * @date: 2019/7/9 14:06
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        // ARouter配置
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
