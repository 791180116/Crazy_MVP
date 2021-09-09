package com.crazy.demo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crazy.demo.app.AppActivity;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *
 *    time   : 2020/11/29
 *    desc   : 重启应用
 */
public final class RestartActivity extends AppActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RestartActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return 0;
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
        restart(this);
        finish();
        toast(R.string.common_crash_hint);
    }

    public static void restart(Context context) {
        Intent intent;
        if (true) {
            // 如果是未登录的情况下跳转到闪屏页
            intent = new Intent(context, SplashActivity.class);
        } else {
            // 如果是已登录的情况下跳转到首页
            intent = new Intent(context, HomeActivity.class);
        }

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}