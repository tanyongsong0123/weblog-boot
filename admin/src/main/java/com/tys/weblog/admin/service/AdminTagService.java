package com.tys.weblog.admin.service;


import com.tys.weblog.admin.modle.vo.tag.AddTagReqVO;
import com.tys.weblog.admin.modle.vo.tag.DeleteTagReqVO;
import com.tys.weblog.admin.modle.vo.tag.FindTagPageListReqVO;
import com.tys.weblog.admin.modle.vo.tag.SearchTagReqVO;
import com.tys.weblog.common.utils.PageResponse;
import com.tys.weblog.common.utils.Response;


public interface AdminTagService {

    /**
     * 添加标签集合
     * @param addTagReqVO
     * @return
     */
    Response addTags(AddTagReqVO addTagReqVO);
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTag(SearchTagReqVO searchTagReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();

}
