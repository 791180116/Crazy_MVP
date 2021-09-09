package com.crazy.base.mvp;

/**
 * Created by wanglei on 2016/12/29.
 */

public interface IPresent<V> {
    void attachView(V view);

    void detachView();

    boolean hasView();
}
