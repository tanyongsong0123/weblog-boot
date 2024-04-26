package com.tys.weblog.weblogweb.service;

import com.tys.weblog.common.utils.Response;

public interface BlogSettingsService {
    /**
     * 获取博客设置信息
     * @return
     */
    Response findDetail();
}