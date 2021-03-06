package com.aimo.app.ui.activity;


import android.os.Bundle;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.aimo.aiapp.aop.SingleClick;
import com.aimo.app.base.AppActivity;
import com.blankj.utilcode.util.SPUtils;
import com.aimo.aiapp.R;
import com.aimo.aiapp.ui.adapter.GuideAdapter;

import me.relex.circleindicator.CircleIndicator3;

/**
 * author : NG_crazy
 * desc   : 应用引导页
 */
public final class GuideActivity extends AppActivity {

    private ViewPager2 mViewPager;
    private CircleIndicator3 mIndicatorView;
    private View mCompleteView;

    private GuideAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.guide_activity;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View view) {
        mViewPager = findViewById(R.id.vp_guide_pager);
        mIndicatorView = findViewById(R.id.cv_guide_indicator);
        mCompleteView = findViewById(R.id.btn_guide_complete);
        setOnClickListener(mCompleteView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mAdapter = new GuideAdapter(this);
        mAdapter.addItem(R.drawable.guide_1_bg);
        mAdapter.addItem(R.drawable.guide_2_bg);
        mAdapter.addItem(R.drawable.guide_3_bg);
        mViewPager.setAdapter(mAdapter);
        mViewPager.registerOnPageChangeCallback(mCallback);
        mIndicatorView.setViewPager(mViewPager);
    }

    @SingleClick
    @Override
    public void onClick(View view) {
        if (view == mCompleteView) {
            MainActivity.start(getContext());
            SPUtils.getInstance("Start").put("isNotFirst", true);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.unregisterOnPageChangeCallback(mCallback);
    }

    private final ViewPager2.OnPageChangeCallback mCallback = new ViewPager2.OnPageChangeCallback() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mViewPager.getCurrentItem() == mAdapter.getItemCount() - 1 && positionOffsetPixels > 0) {
                mIndicatorView.setVisibility(View.VISIBLE);
                mCompleteView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager2.SCROLL_STATE_IDLE) {
                boolean last = mViewPager.getCurrentItem() == mAdapter.getItemCount() - 1;
                mIndicatorView.setVisibility(last ? View.INVISIBLE : View.VISIBLE);
                mCompleteView.setVisibility(last ? View.VISIBLE : View.INVISIBLE);
            }
        }
    };
}