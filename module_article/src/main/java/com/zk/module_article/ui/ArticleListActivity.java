package com.zk.module_article.ui;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zk.common_base.base.activity.BaseActivity;
import com.zk.common_base.utils.ActivityUtils;
import com.zk.module_article.R;
import com.zk.module_article.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description: 文章列表页面
 * @author: zhukai
 * @date: 2019/7/9 13:28
 */
@Route(path = "/article/articlelist")
public class ArticleListActivity extends BaseActivity {

    @Autowired
    String name;

    @BindView(R2.id.tv_name)
    TextView tvName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_article_list;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("文章列表");
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, getIntent().getStringExtra("name"));
        tvName.setText(name);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @OnClick({R2.id.btn_go_back})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_go_back) {
            setResult(RESULT_OK);
            ActivityUtils.finishActivity(mContext);
        }
    }
}
