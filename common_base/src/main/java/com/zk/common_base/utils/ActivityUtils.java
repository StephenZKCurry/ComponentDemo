package com.zk.common_base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zk.common_base.R;

/**
 * author: zhukai
 * created on: 2017/11/30
 * description:Activity跳转工具类
 */
public class ActivityUtils {

    /**
     * 跳转Activity
     *
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 跳转Activity
     *
     * @param activity
     * @param intent
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 关闭Activity
     *
     * @param context
     */
    public static void finishActivity(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
