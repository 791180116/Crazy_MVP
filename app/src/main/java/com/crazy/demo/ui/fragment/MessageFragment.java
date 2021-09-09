package com.crazy.demo.ui.fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.crazy.demo.app.AppFragment;
import com.crazy.demo.app.TitleBarFragment;
import com.crazy.demo.http.glide.GlideApp;
import com.crazy.demo.R;
import com.crazy.demo.aop.Permissions;
import com.crazy.demo.aop.SingleClick;
import com.crazy.demo.ui.activity.HomeActivity;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

/**
 *    author : NG_crazy
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 消息 Fragment
 */
public final class MessageFragment extends TitleBarFragment<HomeActivity> {

    private ImageView mImageView;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        
    }

    @Override
    public int getLayoutId() {
        return R.layout.message_fragment;
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

            HomeActivity.start(getActivity(), (Class<? extends AppFragment<?>>) HomeFragment.class);
        }
    }

    @Permissions(Permission.CAMERA)
    private void requestPermission() {
        toast("获取摄像头权限成功");
    }
}