package com.tys.weblog.admin.modle.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "SearchTagReqVO")
public class SearchTagReqVO {

    @NotEmpty(message = "查询关键词不能为空")
    private String key;

}
