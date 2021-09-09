package com.crazy.base.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by wanglei on 2016/12/29.
 */

public interface IView<P> {
    void bindUI(View rootView);

    void bindEvent();

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();

    /**
     * 获取布局 ID
     */
    int getLayoutId();

    /**
     * 是否启用EventBus
     */
    boolean useEventBus();

    /**
     * 新建Present
     */
    P newPresent();
}
