package com.tys.weblog.weblogweb.controller;

import com.tys.weblog.common.aspect.ApiOperationLog;
import com.tys.weblog.common.enums.ResponseCodeEnum;
import com.tys.weblog.common.exception.BizException;
import com.tys.weblog.common.utils.JsonUtil;
import com.tys.weblog.common.utils.Response;
import com.tys.weblog.weblogweb.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api("test")
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation("test1111111")
    public User test(@RequestBody User user) {
        // 返参
        return user;
    }
    @PostMapping("/test1")
    @ApiOperationLog(description = "测试接口")
    public Response<String> test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 是否存在校验错误
        if (bindingResult.hasErrors()) {
            // 获取校验不通过字段的提示信息
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return Response.fail(errorMsg);
        }

        // 返参
        return Response.success();
    }

    @PostMapping("/test2")
    @ApiOperationLog(description = "测试接口")
    public Response test2(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试接口")
    public Response test3(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 主动定义一个运行时异常，分母不能为零
        int i = 1 / 0;
        return Response.success();
    }

    @PostMapping("/test4")
    @ApiOperationLog(description = "测试接口")
    public Response test4(@RequestBody @Validated User user) {
        return Response.success();
    }

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response testDate(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
    }

    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate() {
        log.info("更新成功...");
        return Response.success();
    }
}
