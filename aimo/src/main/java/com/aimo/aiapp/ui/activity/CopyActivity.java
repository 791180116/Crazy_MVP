package com.aimo.aiapp.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.aimo.aiapp.app.AppActivity;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyActivity extends AppActivity {

    @Override
    public int getLayoutId() {
        return R.layout.copy_activity;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View view) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}