package com.zk.common_base.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.common_base.R;
import com.zk.common_base.rxbus.RxBus;
import com.zk.common_base.utils.ToastUtils;
import com.zk.common_base.widgets.WaitPorgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @description: 普通Fragment基类
 * @author: zhukai
 * @date: 2018/3/2 14:01
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mRootView;
    protected WaitPorgressDialog mWaitPorgressDialog;
    protected TextView tvTitle;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        // 注册RxBus
        RxBus.get().register(this);
        // 注入ARouter
        ARouter.getInstance().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getContentViewId(), null);
            initView(mRootView);
            initData();
            initEvent();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        // 取消注册RxBus
        RxBus.get().unRegister(mContext);
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
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        mWaitPorgressDialog = new WaitPorgressDialog(mContext);
        tvTitle = view.findViewById(R.id.tv_title);
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
     * @param message 加载提示字符串
     */
    protected void showLoading(String message) {
        if (mWaitPorgressDialog.isShowing()) {
            mWaitPorgressDialog.dismiss();
        }
        mWaitPorgressDialog.setMessage(message);
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
    protected boolean hideKeyboard() {
        // 点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) mContext.getSystemService
                (Context.INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(getActivity()
                .getCurrentFocus().getWindowToken(), 0);
    }
}
