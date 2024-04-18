package com.tys.weblog.admin.service;

import com.tys.weblog.admin.modle.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.tys.weblog.common.utils.Response;

public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
}