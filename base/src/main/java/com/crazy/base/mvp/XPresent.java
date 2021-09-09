package com.crazy.base.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by wanglei on 2016/12/29.
 */

public class XPresent<V extends IView> implements IPresent<V> {
    private WeakReference<V> v;

    @Override
    public void attachView(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getView() {
        if (v == null || v.get() == null) {
            throw new IllegalStateException("v can not be null");
        }
        return v.get();
    }


    @Override
    public boolean hasView() {
        return v != null && v.get() != null;
    }
}
