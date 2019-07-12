package com.zk.module_main.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.common_base.base.activity.BaseActivity;
import com.zk.module_main.R;
import com.zk.module_main.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description: 首页
 * @author: zhukai
 * @date: 2019/7/9 14:18
 */
public class MainActivity extends BaseActivity {

    @BindView(R2.id.btn_article_list)
    Button btnArticleList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("首页");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @OnClick({R2.id.btn_article_list})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_article_list) {
            // 跳转文章列表页面
            ARouter.getInstance().build("/article/articlelist")
                    .withString("name", "StephenCurry")
                    .navigation(this, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.e(TAG, "执行了onActivityResult");
        }
    }
}
