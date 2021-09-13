package com.aimo.aiapp.ui.fragment;

import android.os.Bundle;

import com.aimo.app.base.AppFragment;
import com.aimo.aiapp.R;
import com.aimo.aiapp.ui.activity.CopyActivity;

/**
 * author : NG_crazy
 * 
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class CopyFragment extends AppFragment<CopyActivity> {

    public static CopyFragment newInstance() {
        return new CopyFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.copy_fragment;
    }

    @Override
    public Object newPresent() {
        return null;
    }


}