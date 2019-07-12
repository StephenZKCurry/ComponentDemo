package com.zk.module_main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zk.common_base.base.activity.BaseActivity;
import com.zk.module_main.R;

/**
 * @description: java类作用描述
 * @author: zhukai
 * @date: 2019/7/9 17:25
 */
@Route(path = "/main/test")
public class TestActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
}
