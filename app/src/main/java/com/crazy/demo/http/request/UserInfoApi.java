package com.crazy.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : NG_crazy
 *    time   : 2019/12/07
 *    desc   : 获取用户信息
 */
public final class UserInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/info";
    }
}