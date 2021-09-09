package com.aimo.aiapp.ui.popup;

import android.content.Context;

import com.crazy.base.BasePopupWindow;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *    time   : 2019/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyPopup {

    public static final class Builder
            extends BasePopupWindow.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.copy_popup);
        }
    }
}