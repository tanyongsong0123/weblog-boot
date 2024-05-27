package com.tys.weblog.weblogweb.service;

import com.tys.weblog.common.utils.Response;
import com.tys.weblog.weblogweb.model.vo.article.FindArticleDetailReqVO;
import com.tys.weblog.weblogweb.model.vo.article.FindIndexArticlePageListReqVO;

public interface ArticleService {
    /**
     * 获取首页文章分页数据
     * @param findIndexArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    /**
     * 获取文章详情
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);

}