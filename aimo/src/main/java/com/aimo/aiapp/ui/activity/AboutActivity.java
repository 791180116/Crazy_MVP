package com.aimo.aiapp.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.aimo.app.base.AppActivity;
import com.aimo.aiapp.R;

/**
 *    author : NG_crazy
 *    time   : 2018/10/18
 *    desc   : 关于界面
 */
public final class AboutActivity extends AppActivity {

    @Override
    public int getLayoutId() {
        return R.layout.about_activity;
    }

    @Override
    public void bindUI(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public Object newPresent() {
        return null;
    }
}