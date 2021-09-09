package com.crazy.base.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;

import com.crazy.base.XDroidConf;
import com.crazy.base.event.BusProvider;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Created by wanglei on 2016/12/29.
 */

public abstract class XActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    private RxPermissions rxPermissions;

    //private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getPresent();
        initActivity(savedInstanceState);
    }

    protected void initActivity(Bundle savedInstanceState) {
        initLayout();
        initData(savedInstanceState);
    }

    protected void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }
    }

    @Override
    public void bindUI(View rootView) {
        //unbinder = KnifeKit.bind(this);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getPresent() {
        if (p == null) {
            p = newPresent();
        }
        if (p != null) {
            if (!p.hasView()) {
                p.attachView(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getPresent() != null) {
            getPresent().detachView();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }
}
