package com.crazy.demo.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.crazy.demo.action.StatusAction;
import com.crazy.demo.app.AppActivity;
import com.crazy.demo.widget.StatusLayout;
import com.crazy.demo.R;
import com.crazy.demo.ui.dialog.MenuDialog;

/**
 *    author : NG_crazy
 *    time   : 2019/04/17
 *    desc   : 加载使用案例
 */
public final class StatusActivity extends AppActivity
        implements StatusAction {

    private StatusLayout mStatusLayout;

    @Override
    public int getLayoutId() {
        return R.layout.status_activity;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View view) {
        mStatusLayout = findViewById(R.id.hl_status_hint);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        new MenuDialog.Builder(this)
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList("加载中", "请求错误", "空数据提示", "自定义提示")
                .setListener((dialog, position, object) -> {
                    switch (position) {
                        case 0:
                            showLoading();
                            postDelayed(this::showComplete, 2500);
                            break;
                        case 1:
                            showError(v -> {
                                showLoading();
                                postDelayed(this::showEmpty, 2500);
                            });
                            break;
                        case 2:
                            showEmpty();
                            break;
                        case 3:
                            showLayout(ContextCompat.getDrawable(getActivity(), R.drawable.status_order_ic), "暂无订单", null);
                            break;
                        default:
                            break;
                    }
                })
                .show();
    }

    @Override
    public StatusLayout getStatusLayout() {
        return mStatusLayout;
    }
}