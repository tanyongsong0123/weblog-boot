package com.tys.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tys.weblog.admin.convert.BlogSettingsConvert;
import com.tys.weblog.admin.modle.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.tys.weblog.admin.service.AdminBlogSettingsService;
import com.tys.weblog.common.domain.dos.BlogSettingsDO;
import com.tys.weblog.common.domain.mapper.BlogSettingsMapper;
import com.tys.weblog.common.utils.Response;
import org.springframework.stereotype.Service;

@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
//        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
//                .id(1L)
//                .logo(updateBlogSettingsReqVO.getLogo())
//                .name(updateBlogSettingsReqVO.getName())
//                .author(updateBlogSettingsReqVO.getAuthor())
//                .introduction(updateBlogSettingsReqVO.getIntroduction())
//                .avatar(updateBlogSettingsReqVO.getAvatar())
//                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
//                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
//                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
//                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
//                .build();
        // VO 转 DO
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);

        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
}
