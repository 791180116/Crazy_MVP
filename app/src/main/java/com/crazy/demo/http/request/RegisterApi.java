package com.crazy.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : NG_crazy
 *    time   : 2019/12/07
 *    desc   : 用户注册
 */
public final class RegisterApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/register";
    }

    /** 手机号 */
    private String phone;
    /** 验证码 */
    private String code;
    /** 密码 */
    private String password;

    public RegisterApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegisterApi setCode(String code) {
        this.code = code;
        return this;
    }

    public RegisterApi setPassword(String password) {
        this.password = password;
        return this;
    }
}