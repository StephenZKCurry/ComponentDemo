package com.zk.common_base.base.fragment;

import com.zk.common_base.R;
import com.zk.common_base.base.BasePresenter;
import com.zk.common_base.base.IBaseModel;
import com.zk.common_base.base.IBaseView;

/**
 * @description: MVP Fragment基类
 * @author: zhukai
 * @date: 2018/3/3 13:11
 */
public abstract class BaseMVPFragment<P extends BasePresenter, M extends IBaseModel> extends
        LazyFragment implements IBaseView {

    // presenter,具体的presenter由子类确定
    protected P mPresenter;
    // model,具体的model由子类确定
    protected M mIModel;

    @Override
    protected void initData() {
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIModel = (M) mPresenter.getModel();
            if (mIModel != null) {
                // 绑定model和view
                mPresenter.attachMV(mIModel, this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            // 解绑model和view
            mPresenter.detachMV();
        }
    }

    /**
     * 弹出Toast
     *
     * @param msg 要显示的toast消息字符串
     */
    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    /**
     * 显示加载框
     *
     * @param msg 加载提示字符串
     */
    @Override
    public void showLoading(String msg) {
        super.showLoading(msg);
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    /**
     * 请求成功
     *
     * @param message 成功信息
     */
    @Override
    public void showSuccess(String message) {
        showToast(message);
    }

    /**
     * 请求失败
     *
     * @param message 失败信息
     */
    @Override
    public void showFaild(String message) {
        showToast(message);
    }

    /**
     * 显示当前网络不可用
     */
    @Override
    public void showNoNet() {
        showToast(getString(R.string.no_network_connection));
    }
}
