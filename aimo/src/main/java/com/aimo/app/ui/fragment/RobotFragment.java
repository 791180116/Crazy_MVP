package com.aimo.app.ui.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.aimo.app.base.AppFragment;
import com.aimo.app.base.TitleBarFragment;
import com.aimo.aiapp.http.model.HttpData;
import com.aimo.aiapp.http.request.getVehicleBaseInfoApi;
import com.aimo.aiapp.ui.fragment.BrowserFragment;
import com.aimo.aiapp.ui.fragment.StatusFragment;
import com.blankj.utilcode.util.LogUtils;
import com.crazy.base.FragmentPagerAdapter;
import com.aimo.aiapp.widget.XCollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.aimo.aiapp.R;
import com.aimo.app.ui.activity.MainActivity;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

/**
 * author : NG_crazy
 * desc   : 首页 Fragment
 */
public final class RobotFragment extends TitleBarFragment<MainActivity>
        implements XCollapsingToolbarLayout.OnScrimsListener {

    private XCollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;

    private TextView mAddressView;
    private TextView mHintView;
    private AppCompatImageView mSearchView;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter<AppFragment<?>> mPagerAdapter;

    public static RobotFragment newInstance() {
        return new RobotFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EasyHttp.get(this)
                .api(new getVehicleBaseInfoApi().setDeptId("10"))
                .request(new OnHttpListener<HttpData<Object>>() {
                    @Override
                    public void onSucceed(HttpData<Object> result) {
                        LogUtils.d(result.getData().toString());
                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.robot_fragment;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View rootView) {
        mCollapsingToolbarLayout = findViewById(R.id.ctl_home_bar);
        mToolbar = findViewById(R.id.tb_home_title);

        mAddressView = findViewById(R.id.tv_home_address);
        mHintView = findViewById(R.id.tv_home_hint);
        mSearchView = findViewById(R.id.iv_home_search);

        mTabLayout = findViewById(R.id.tl_home_tab);
        mViewPager = findViewById(R.id.vp_home_pager);

        mPagerAdapter = new FragmentPagerAdapter<>(this);
        mPagerAdapter.addFragment(StatusFragment.newInstance(), "列表演示");
        mPagerAdapter.addFragment(BrowserFragment.newInstance("https://github.com/getActivity"), "网页演示");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean isStatusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     * <p>
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
            mHintView.setBackgroundResource(R.drawable.home_search_bar_gray_bg);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black60));
            mSearchView.setSupportImageTintList(ColorStateList.valueOf(getColor(R.color.common_icon_color)));
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mHintView.setBackgroundResource(R.drawable.home_search_bar_transparent_bg);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
            mSearchView.setSupportImageTintList(ColorStateList.valueOf(getColor(R.color.white)));
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }
}