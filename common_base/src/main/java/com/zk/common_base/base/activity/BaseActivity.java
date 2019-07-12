package com.zk.common_base.base.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.common_base.R;
import com.zk.common_base.manager.AppManager;
import com.zk.common_base.rxbus.RxBus;
import com.zk.common_base.utils.ActivityUtils;
import com.zk.common_base.utils.SystemStatusManager;
import com.zk.common_base.utils.ToastUtils;
import com.zk.common_base.widgets.WaitPorgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @description: 普通Activity基类
 * @author: zhukai
 * @date: 2018/3/2 11:46
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected WaitPorgressDialog mWaitPorgressDialog;
    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected Button btnBack;
    protected TextView tvRight;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 将activity添加到任务栈
        AppManager.getAppManager().addActivity(this);
        // 注册RxBus
        RxBus.get().register(this);
        // 注入ARouter
        ARouter.getInstance().inject(this);
        setContentView(getContentViewId());
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 将activity从任务栈中移出
        AppManager.getAppManager().finishActivity(this);
        // 取消注册RxBus
        RxBus.get().unRegister(mContext);
        unbinder.unbind();
    }

    /**
     * 设置布局资源id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化页面
     */
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        mWaitPorgressDialog = new WaitPorgressDialog(this);
        // 设置状态栏颜色，实现沉浸式效果
        new SystemStatusManager(this).setTranslucentStatus(R.color.color_main);
        // 采用ToolBar作为标题栏
//        initToolBar();
        // 采用自定义布局作为标题栏
        tvTitle = findViewById(R.id.tv_title);
        tvRight = findViewById(R.id.tv_right);
        btnBack = findViewById(R.id.btn_back);
        if (showHomeAsUp()) {
            btnBack.setVisibility(View.VISIBLE);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.finishActivity(mContext);
                }
            });
        } else {
            btnBack.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化事件
     */
    protected void initEvent() {
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (mToolbar == null) {
            throw new NullPointerException("toolbar can not be null");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // toolbar除掉阴影
        getSupportActionBar().setElevation(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(0);
        }
    }

    /**
     * 标题栏是否显示返回键
     *
     * @return
     */
    protected boolean showHomeAsUp() {
        return false;
    }

    /**
     * 点击toolbar返回
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtils.finishActivity(mContext);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 弹出Toast
     *
     * @param msg 要显示的toast消息字符串
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    /**
     * 显示加载框
     *
     * @param msg 加载提示字符串
     */
    protected void showLoading(String msg) {
        if (mWaitPorgressDialog.isShowing()) {
            mWaitPorgressDialog.dismiss();
        }
        mWaitPorgressDialog.setMessage(msg);
        mWaitPorgressDialog.show();
    }

    /**
     * 隐藏加载框
     */
    protected void hideLoading() {
        if (mWaitPorgressDialog != null) {
            mWaitPorgressDialog.dismiss();
        }
    }

    /**
     * 隐藏软键盘
     *
     * @return true:隐藏成功 false:隐藏失败
     */
    protected boolean hiddenKeyboard() {
        // 点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }
}
