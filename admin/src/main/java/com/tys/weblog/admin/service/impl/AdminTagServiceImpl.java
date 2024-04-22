package com.tys.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tys.weblog.admin.modle.vo.tag.*;
import com.tys.weblog.admin.service.AdminTagService;
import com.tys.weblog.common.domain.dos.ArticleTagRelDO;
import com.tys.weblog.common.domain.dos.TagDO;
import com.tys.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.tys.weblog.common.domain.mapper.TagMapper;
import com.tys.weblog.common.enums.ResponseCodeEnum;
import com.tys.weblog.common.exception.BizException;
import com.tys.weblog.common.model.vo.SelectRspVO;
import com.tys.weblog.common.utils.PageResponse;
import com.tys.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @url: www.tys.com
 * @date: 2023-09-15 14:03
 * @description: TODO
 **/
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加标签集合
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTags(AddTagReqVO addTagReqVO) {
        // vo 转 do
        List<TagDO> tagDOS = addTagReqVO.getTags().stream()
                .map(tagName -> TagDO.builder()
                        .name(tagName.trim()) // 去掉前后空格
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        // 批量插入
        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }

        return Response.success();
    }

    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        String name = findTagPageListReqVO.getName();
        Page<TagDO> tagDOPage = tagMapper.selectPageList(current, size, name, startDate, endDate);

        List<TagDO> tagDOS = tagDOPage.getRecords();
        List<FindTagPageListRspVO> collect = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            collect = tagDOS.stream().map(tagDO -> FindTagPageListRspVO.builder()
                    .id(tagDO.getId())
                    .name(tagDO.getName())
                    .createTime(tagDO.getCreateTime())
                    .build()).collect(Collectors.toList());
        }
        return PageResponse.success(tagDOPage, collect);

    }

//    @Override
//    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
//        // 分类 ID
//        Long categoryId = deleteTagReqVO.getId();
//
//        // 删除分类
//        int i = tagMapper.deleteById(categoryId);
//        if (i <= 0) {
//            return Response.fail(TAG_NOT_EXIET);
//        }
//        return Response.success();
//    }

    @Override
    public Response searchTag(SearchTagReqVO searchTagReqVO) {
        String key = searchTagReqVO.getKey();

        List<TagDO> tagDOS = tagMapper.selectByKey(key);
        List<SelectRspVO> collect = null;


        if (!CollectionUtils.isEmpty(tagDOS)) {
            collect = tagDOS.stream().map(tagDO -> SelectRspVO
                            .builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId()).build())
                    .collect(Collectors.toList());
        }
        return Response.success(collect);
    }

    @Override
    public Response findTagSelectList() {
        // 查询所有标签, Wrappers.emptyWrapper() 表示查询条件为空
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    /**
     * 删除标签
     *
     * @param deleteTagReqVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRelDO)) {
            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }
}
