package com.tys.weblog.weblogweb.service;

import com.tys.weblog.common.utils.Response;

public interface CategoryService {
    /**
     * 获取分类列表
     * @return
     */
    Response findCategoryList();
}