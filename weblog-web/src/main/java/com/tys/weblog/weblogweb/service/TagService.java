package com.tys.weblog.weblogweb.service;

import com.tys.weblog.common.utils.Response;

public interface TagService {
    /**
     * 获取标签列表
     * @return
     */
    Response findTagList();
}