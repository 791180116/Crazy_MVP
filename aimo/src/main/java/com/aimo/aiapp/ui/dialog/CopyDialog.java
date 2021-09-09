package com.aimo.aiapp.ui.dialog;

import android.content.Context;
import android.view.Gravity;

import com.crazy.base.BaseDialog;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.copy_dialog);
            setAnimStyle(BaseDialog.ANIM_BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}