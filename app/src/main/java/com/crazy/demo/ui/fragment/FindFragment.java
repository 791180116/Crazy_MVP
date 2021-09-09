package com.crazy.demo.ui.fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.crazy.demo.app.TitleBarFragment;
import com.crazy.demo.http.glide.GlideApp;
import com.crazy.widget.view.CountdownView;
import com.crazy.widget.view.SwitchButton;
import com.crazy.demo.R;
import com.crazy.demo.aop.SingleClick;
import com.crazy.demo.ui.activity.HomeActivity;

/**
 * author : NG_crazy
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 发现 Fragment
 */
public final class FindFragment extends TitleBarFragment<HomeActivity>
        implements SwitchButton.OnCheckedChangeListener {

    private ImageView mCircleView;
    private ImageView mCornerView;
    private SwitchButton mSwitchButton;
    private CountdownView mCountdownView;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.find_fragment;
    }

    @Override
    public void bindUI(View view) {
        mCircleView = findViewById(R.id.iv_find_circle);
        mCornerView = findViewById(R.id.iv_find_corner);
        mSwitchButton = findViewById(R.id.sb_find_switch);
        mCountdownView = findViewById(R.id.cv_find_countdown);
        setOnClickListener(mCountdownView);

        mSwitchButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // 显示圆形的 ImageView
        GlideApp.with(this)
                .load(R.drawable.example_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                .into(mCircleView);

        // 显示圆角的 ImageView
        GlideApp.with(this)
                .load(R.drawable.example_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()))))
                .into(mCornerView);
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @SingleClick
    @Override
    public void onClick(View view) {
        if (view == mCountdownView) {
            toast(R.string.common_code_send_hint);
            mCountdownView.start();
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean checked) {
        toast(checked);
    }
}