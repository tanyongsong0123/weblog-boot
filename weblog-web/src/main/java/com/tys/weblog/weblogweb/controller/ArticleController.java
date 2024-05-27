package com.tys.weblog.weblogweb.controller;

import com.tys.weblog.common.aspect.ApiOperationLog;
import com.tys.weblog.common.utils.Response;
import com.tys.weblog.weblogweb.model.vo.article.FindArticleDetailReqVO;
import com.tys.weblog.weblogweb.model.vo.article.FindIndexArticlePageListReqVO;
import com.tys.weblog.weblogweb.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/article")
@Api(tags = "文章")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/list")
    @ApiOperation(value = "获取首页文章分页数据")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Response findArticlePageList(@RequestBody FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        return articleService.findArticlePageList(findIndexArticlePageListReqVO);
    }
    @PostMapping("/detail")
    @ApiOperation(value = "获取文章详情")
    @ApiOperationLog(description = "获取文章详情")
    public Response findArticleDetail(@RequestBody FindArticleDetailReqVO findArticleDetailReqVO) {
        return articleService.findArticleDetail(findArticleDetailReqVO);
    }

}