package com.aimo.app.ui.fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.aimo.aiapp.http.glide.GlideApp;
import com.aimo.app.base.TitleBarFragment;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.aimo.app.base.AppFragment;
import com.aimo.aiapp.R;
import com.aimo.aiapp.aop.Permissions;
import com.aimo.aiapp.aop.SingleClick;
import com.aimo.app.ui.activity.MainActivity;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

/**
 *    author : NG_crazy
 *    desc   : 任务 Fragment
 */
public final class TaskFragment extends TitleBarFragment<MainActivity> {

    private ImageView mImageView;

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        
    }

    @Override
    public int getLayoutId() {
        return R.layout.task_fragment;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View view) {
        mImageView = findViewById(R.id.iv_message_image);
        setOnClickListener(R.id.btn_message_image1, R.id.btn_message_image2, R.id.btn_message_image3,
                R.id.btn_message_toast, R.id.btn_message_permission, R.id.btn_message_setting,
                R.id.btn_message_black, R.id.btn_message_white, R.id.btn_message_tab);
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @SingleClick
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_message_image1) {

            mImageView.setVisibility(View.VISIBLE);
            GlideApp.with(this)
                    .load("https://www.baidu.com/img/bd_logo.png")
                    .into(mImageView);

        } else if (viewId == R.id.btn_message_image2) {

            mImageView.setVisibility(View.VISIBLE);
            GlideApp.with(this)
                    .load("https://www.baidu.com/img/bd_logo.png")
                    .circleCrop()
                    .into(mImageView);

        } else if (viewId == R.id.btn_message_image3) {

            mImageView.setVisibility(View.VISIBLE);
            GlideApp.with(this)
                    .load("https://www.baidu.com/img/bd_logo.png")
                    .transform(new RoundedCorners((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            20, getResources().getDisplayMetrics())))
                    .into(mImageView);

        } else if (viewId == R.id.btn_message_toast) {

            toast("我是吐司");

        } else if (viewId == R.id.btn_message_permission) {

            requestPermission();

        } else if (viewId == R.id.btn_message_setting) {

            XXPermissions.startApplicationDetails(this);

        } else if (viewId == R.id.btn_message_black) {

            getAttachActivity().getStatusBarConfig().statusBarDarkFont(true).init();

        } else if (viewId == R.id.btn_message_white) {

            getAttachActivity().getStatusBarConfig().statusBarDarkFont(false).init();

        } else if (viewId == R.id.btn_message_tab) {

            MainActivity.start(getActivity(), (Class<? extends AppFragment<?>>) RobotFragment.class);
        }
    }

    @Permissions(Permission.CAMERA)
    private void requestPermission() {
        toast("获取摄像头权限成功");
    }
}