package debug;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.common_base.base.activity.BaseActivity;
import com.zk.module_article.R;
import com.zk.module_article.R2;

import butterknife.OnClick;

/**
 * @description: 可以在此Activity中进行一些初始化工作，比如模拟用户登录，用于组件化调试
 * @author: zhukai
 * @date: 2019/7/9 11:56
 */
public class IndexActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("初始化页面");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @OnClick({R2.id.btn_init})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_init) {
            // 跳转文章列表页面
            ARouter.getInstance().build("/article/articlelist")
                    .withString("name", "StephenCurry")
                    .navigation(this);
        }
    }
}
