package com.zk.common_base.net.rx;

import com.google.gson.JsonParseException;
import com.zk.common_base.R;
import com.zk.common_base.base.BaseApplication;
import com.zk.common_base.base.IBaseView;
import com.zk.common_base.net.http.ServerException;
import com.zk.common_base.utils.NetworkUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

/**
 * @description: RxJava Observer
 * @author: zhukai
 * @date: 2019/4/11 15:35
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    private IBaseView mIView;

    protected BaseObserver(IBaseView view) {
        this.mIView = view;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        String message = null;
        if (!NetworkUtils.isAvailable(BaseApplication.getContext())) {
            message = BaseApplication.getContext().getString(R.string.no_network_connection);
        } else if (e instanceof SocketTimeoutException) {
            message = BaseApplication.getContext().getString(R.string.request_error);
        } else if (e instanceof ConnectException ||
                e instanceof UnknownHostException) {
            // 均视为网络错误
            message = BaseApplication.getContext().getString(R.string.network_error);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            message = BaseApplication.getContext().getString(R.string.json_parse_error);
        } else if (e instanceof IllegalArgumentException) {
            message = BaseApplication.getContext().getString(R.string.param_error);
        } else if (e instanceof ServerException) {
            // 服务器出错
            message = ((ServerException) e).message;
        } else {
            message = BaseApplication.getContext().getString(R.string.unknown_error);
        }
        onFailure(message);
    }

    /**
     * 请求成功
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 请求失败
     *
     * @param message
     */
    public void onFailure(String message) {
        mIView.showFaild(message);
    }
}
