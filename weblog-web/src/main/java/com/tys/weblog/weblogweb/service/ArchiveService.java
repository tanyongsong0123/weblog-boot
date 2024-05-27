package com.tys.weblog.weblogweb.service;

import com.tys.weblog.common.utils.Response;
import com.tys.weblog.weblogweb.model.vo.archive.FindArchiveArticlePageListReqVO;

public interface ArchiveService {
    /**
     * 获取文章归档分页数据
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}