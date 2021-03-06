package com.aimo.aiapp.http.model;

import com.aimo.aiapp.other.AppConfig;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.BodyType;

/**
 *    author : NG_crazy
 *    time   : 2020/10/02
 *    desc   : 服务器配置
 */
public class RequestServer implements IRequestServer {

    @Override
    public String getHost() {
        return AppConfig.getHostUrl();
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    public BodyType getType() {
        // 以表单的形式提交参数
        return BodyType.FORM;
    }
}