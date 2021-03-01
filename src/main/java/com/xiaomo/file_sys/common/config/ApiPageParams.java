package com.xiaomo.file_sys.common.config;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.*;


/**
 * Description: 自定义注解减化swagger注解（分页）
 * Created by mc on 2020/2/20 0:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiImplicitParams({
        @ApiImplicitParam(name="pageSize", value="每页条数", required=true),
        @ApiImplicitParam(name="pageNum", value="第几页", required=true),
        @ApiImplicitParam(name="sort", value="排序字段"),
        @ApiImplicitParam(name="order", value="升/降 ascending升 descending降", defaultValue = "ascending")
})
public @interface ApiPageParams {
}
