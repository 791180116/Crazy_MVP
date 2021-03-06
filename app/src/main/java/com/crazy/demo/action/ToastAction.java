package com.crazy.demo.action;

import androidx.annotation.StringRes;

import com.hjq.toast.ToastUtils;

/**
 *    author : NG_crazy
 *    time   : 2019/12/08
 *    desc   : 吐司意图
 */
public interface ToastAction {

    default void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    default void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    default void toast(Object object) {
        ToastUtils.show(object);
    }
}